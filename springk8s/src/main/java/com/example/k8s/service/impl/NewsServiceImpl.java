package com.example.k8s.service.impl;

import com.example.k8s.mapper.NewsMapper;
import com.example.k8s.model.News;
import com.example.k8s.service.NewsService;
import com.example.k8s.untils.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zzg on 2018/1/1.
 */
@Service(value = "newsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;//这里会报错，但是并不会影

    @Override
    public int add(News news) {
        Date date = new Date();
        news.setNewtime(date.getTime());
        newsMapper.insertSelective(news);
        return news.getId();
    }

    @Override
    public News selectByNewstitle(String newstitle) {
        return newsMapper.selectByNewstitle(newstitle);
    }

    @Override
    public void delete(Integer[] ids) {
        if (ids != null && ids.length > 0){
            for (int i=0 ; i < ids.length ; i++) {
                if (newsMapper.selectByPrimaryKey(ids[i]) != null){
                    newsMapper.deleteByPrimaryKey(ids[i]);
                }
            }
        }
    }

    @Override
    public void update(News news) {
        newsMapper.updateByPrimaryKeySelective(news);
    }

    @Override
    public News selectByPrimaryKey(Integer id) {
        return newsMapper.selectByPrimaryKey(id);
    }


    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public PageResult<News> selectAllNews(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageResult<News> pageResult = new PageResult<>();
        PageHelper.startPage(pageNum, pageSize);
        List<News> recordList  =  newsMapper.selectAllNews();
        PageInfo<News> pageInfo = new PageInfo<>(recordList);
        pageResult.setRecordsTotal(pageInfo.getTotal());
        pageResult.setItems(recordList);
        return pageResult;
    }
}
