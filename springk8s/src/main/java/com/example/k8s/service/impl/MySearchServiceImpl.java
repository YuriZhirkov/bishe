package com.example.k8s.service.impl;

import com.example.k8s.model.Goods;
import com.example.k8s.service.MySearchService;
import com.example.k8s.untils.PageResult;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.SearchResult;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zzg on 2018/4/4.
 */
@Service(value = "mySearchService")
public class MySearchServiceImpl implements MySearchService {
    @Autowired
    private JestService jestService;


    public String index(List<Object> objs, String indexName, String typeName) throws Exception {
        JestClient jestClient = jestService.getJestClient();
        boolean result = jestService.index(jestClient, indexName, typeName, objs);
        if (result) {
            return "succeed";
        } else {
            return "fail";
        }
    }

    @Override
    public void delete(String indexName, String typeName, String id) throws Exception {
        JestClient jestClient = jestService.getJestClient();
        Delete.Builder builder = new Delete.Builder(id);
        builder.refresh(true);
        builder.index(indexName);
        builder.type(typeName);
        jestClient.execute(builder.build());
    }

    @Override
    public void update(String indexName, String typeName, Goods goods) throws Exception {
        JestClient jestClient = jestService.getJestClient();
        JestResult result = jestService.get(jestClient, indexName, typeName, goods.getId().toString());
        if (result.isSucceeded()) {
            List<Object> objs = new ArrayList<Object>();
            objs.add(goods);
            jestService.index(jestClient, indexName, typeName, objs);
        }
    }

    @Override
    public PageResult<Goods> qurey(String indexName, String typeName, String keyword) throws Exception {
        JestClient jestClient = jestService.getJestClient();
        PageResult<Goods> pageResult = new PageResult<>();
        pageResult.setRecordsTotal(0L);
        List<Goods> list = new ArrayList<>();
        pageResult.setItems(list);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("goodsname", keyword))
                .should(QueryBuilders.matchQuery("detail", keyword))
                .should(QueryBuilders.matchQuery("type", keyword));
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        List<String> high = new ArrayList<>();
        high.add("goodsname");
        high.add("detail");
        high.add("type");
        for (String temp : high) {
            highlightBuilder.field(temp);//高亮field
        }
        highlightBuilder.preTags("<em>").postTags("</em>");//高亮标签
        highlightBuilder.fragmentSize(500);//高亮内容长度
        searchSourceBuilder.highlighter(highlightBuilder);
        searchSourceBuilder.query(queryBuilder);
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(10);
        String queryStr = searchSourceBuilder.toString();
        System.out.println("查询条件: " + queryStr);
        SearchResult result = jestService.search(jestClient, indexName, typeName, queryStr);
        List<SearchResult.Hit<Goods, Void>> hits = result.getHits(Goods.class);
        System.out.println("Size:" + hits.size());
        for (SearchResult.Hit<Goods, Void> hit : hits) {
            Goods goods = hit.source;
            Map<String, List<String>> map = hit.highlight;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                List<String> value = entry.getValue();
                if (key.equals("goodsname")) {
                    goods.setGoodsname(value.get(0));
                }
                if (key.equals("detail")) {
                    goods.setDetail(value.get(0));
                }
                if (key.equals("type")) {
                    goods.setType(value.get(0));
                }

            }
            list.add(goods);
        }
        pageResult.setItems(list);
        jestService.closeJestClient(jestClient);
        return pageResult;
    }


}
