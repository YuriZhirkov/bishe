package com.example.k8s.service;

import com.example.k8s.model.News;
import com.example.k8s.untils.PageResult;

/**
 * Created by zzg on 2018/1/1.
 */
public interface NewsService {
    int add(News news);

    News selectByNewstitle(String newstitle);

    void delete(Integer[] ids);

    void update(News news);

    News selectByPrimaryKey(Integer id);

    PageResult<News> selectAllNews(int pageNum, int pageSize);

}
