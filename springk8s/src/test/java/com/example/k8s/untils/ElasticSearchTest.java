package com.example.k8s.untils;

import com.example.k8s.model.Goods;
import com.example.k8s.service.impl.JestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zzg on 2018/4/2.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticSearchTest {

    @Autowired
    private JestService jestService;

    private JestClient jestClient;
    private String indexName = "zzg";
    private String typeName = "goods";

    //获取jestClient
    @Before
    public void setUp() throws Exception {
        jestClient = jestService.getJestClient();
    }

    @After
    public void tearDown() throws Exception {
        jestService.closeJestClient(jestClient);
    }

    //批量增加/更新文档可以
    @Test
    public void index() throws Exception {

        List<Object> objs = new ArrayList<Object>();
        Goods goods0 = new Goods(1, "Xiaomi/小米 红米5 plus",
                "Xiaomi/小米 红米5 plus全面屏骁龙处理器智能拍照手机全面屏手机", "789.6",
                "https://192.23.34.123/image/12.npg,https://192.23.34.123/image/1.npg",
                "80", "20", 1, "电子产品类");

        Goods goods2 = new Goods(2, "华为p10",
                "全面屏骁龙处理器智能拍照手机全面屏手机", "1000.6",
                "https://192.23.34.123/image/12.npg,https://192.23.34.123/image/1.npg",
                "1001", "20", 2, "电子产品类");
        objs.add(goods0);
        //objs.add(goods2);
        boolean result = jestService.index(jestClient, "zzg", "goods", objs);
        System.out.println(result);
    }

    //根据id查询获取结果
    @Test
    public void get() throws Exception {
        String id = "1";
        JestResult result = jestService.get(jestClient, indexName, typeName, id);
        if (result.isSucceeded()) {
            Goods goods = result.getSourceAsObject(Goods.class);
            System.out.println(goods.toString());
        }
    }

    //根据id更新goods
    //1.根据id查询出对象，设置对象属性
    //2.插入数据
    @Test
    public void update() throws Exception {
        String id = "2";
        JestResult result = jestService.get(jestClient, indexName, typeName, id);
        if (result.isSucceeded()) {
            Goods goods = result.getSourceAsObject(Goods.class);
            goods.setType("手机类");
            List<Object> objs = new ArrayList<Object>();
            objs.add(goods);
            boolean ret = jestService.index(jestClient, "zzg", "goods", objs);
            System.out.println(ret);
            System.out.println(goods.toString());
        } else {
            System.out.println("没有查询出产品无法更新");
        }
    }

    //查询
    @Test
    public void qurey() {
        String search = "小米手机";
        PageResult<Goods> pageResult = new PageResult<>();
        pageResult.setRecordsTotal(0L);
        List<Goods> list = new ArrayList<>();
        pageResult.setItems(list);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("goodsname", search))
                .should(QueryBuilders.matchQuery("detail", search))
                .should(QueryBuilders.matchQuery("type",search));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        List<String> high = new ArrayList<>();
        high.add("goodsname");
        high.add("detail");
        high.add("type");

        for(String temp : high){
            highlightBuilder.field(temp);//高亮field
        }
        highlightBuilder.preTags("<em>").postTags("</em>");//高亮标签
        highlightBuilder.fragmentSize(500);//高亮内容长度
        searchSourceBuilder.highlighter(highlightBuilder);
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);

        String queryStr = searchSourceBuilder.toString();
        System.out.println("查询条件: "+queryStr);
        try {
            SearchResult result = jestService.search(jestClient, indexName, typeName, queryStr);
//            List<SearchResult.Hit<Map, Void>> hits = result.getHits(Map.class);
//            if (hits != null && hits.size() > 0) {
//                for (SearchResult.Hit<Map, Void> hit : hits) {
//                    Map json = hit.source;
//                    ObjectMapper mapper = new ObjectMapper();
//                    //现将Map转化json字符串->LogInfo
//                    Goods goods = mapper.readValue(mapper.writeValueAsString(json), Goods.class);
//                    list.add(goods);
//                }
//            }
            List<SearchResult.Hit<Goods, Void>> hits = result.getHits(Goods.class);
            System.out.println("Size:" + hits.size());
            for (SearchResult.Hit<Goods, Void> hit : hits) {
                Goods goods = hit.source;
                Map<String,List<String>> map = hit.highlight;
                for (Map.Entry<String,List<String>> entry : map.entrySet()) {
                    String key = entry.getKey();
                    List<String> value = entry.getValue();
                    if (key.equals("goodsname")){
                        goods.setGoodsname(value.get(0));
                    }
                    if (key.equals("detail")){
                        goods.setDetail(value.get(0));
                    }
                    if (key.equals("type")){
                        goods.setType(value.get(0));
                    }
                    //System.out.println("key=" + key + " value=" + value);
                }
                //System.out.println(map);
                list.add(goods);
                System.out.println(goods.toString());
            }
            pageResult.setItems(list);
            jestService.closeJestClient(jestClient);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
