package com.example.k8s.controller;


import com.example.k8s.dto.INewsDelete;
import com.example.k8s.model.News;
import com.example.k8s.service.NewsService;
import com.example.k8s.untils.ApiResponse;
import com.example.k8s.untils.SimpleApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zzg on 2018/1/1.
 */
@Controller
@RequestMapping(value = "/bonsai/news")
public class NewsController {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(NewsController.class);

    @Autowired
    private NewsService newsService;

    /**
     * 添加新闻
     * @param news
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse add(@RequestBody News news){
        if (news == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数不能为空");
        }
        News temp = newsService.selectByNewstitle(news.getNewstitle());
        if (temp != null){
            return new ApiResponse(ApiResponse.CODE_EXIST, "新闻已经存在");
        }
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "添加新闻成功", newsService.add(news));
        } catch (Exception e) {
            logger.error("/bonsai/goods/add error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，添加新闻失败");
        }
    }


    /**
     * 删除新闻
     * @param iNewsDelete
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse delete(@RequestBody INewsDelete iNewsDelete){
        if (iNewsDelete == null || iNewsDelete.getIds().length < 0) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "参数不能为空");
        }
        try {
            newsService.delete(iNewsDelete.getIds());
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "删除成功");
        } catch (Exception e) {
            logger.error("/bonsai/news/delete error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，删除失败");
        }
    }


    /**
     * 更新新闻
     * @param news
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.POST)
    public ApiResponse update(@RequestBody News news){
        if (news == null) {
            return new ApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "新闻的参数不能为空");
        }
        News temp = newsService.selectByPrimaryKey(news.getId());
        if (temp == null){
            return new ApiResponse(ApiResponse.CODE_NOT_EXIST, "新闻不存在");
        }
        try {
            newsService.update(news);
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "更新成功");
        } catch (Exception e) {
            logger.error("/bonsai/news/update error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，更新失败");
        }
    }

    /**得到某个新闻的信息
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public ApiResponse get(@PathVariable("id") int id){
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "获取新闻信息成功",newsService.selectByPrimaryKey(id));
        } catch (Exception e) {
            logger.error("/bonsai/news/get/{id} error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "未知错误，获取新闻信息失败");
        }
    }


    /**
     * 得到所有新闻的信息
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/list/{pageNum}/{pageSize}", produces = MediaType.APPLICATION_JSON_VALUE,method = RequestMethod.GET)
    public ApiResponse list(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize){
        // https://www.cnblogs.com/xiaoxinwt/p/5329840.html Mybatis 的分页插件PageHelper-4.1.1的使用
        if (pageSize == 0){
            pageSize = 5;
        }
        try {
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "获取所有新闻信息成功", newsService.selectAllNews(pageNum,pageSize));
        } catch (Exception e) {
            logger.error("/list/"+pageNum+"/"+pageSize+" error, message:{}", e.getMessage());
            return new ApiResponse(ApiResponse.CODE_OTHER, "获取所有新闻信息失败");
        }
    }
}
