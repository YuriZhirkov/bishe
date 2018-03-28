package com.example.k8s.controller;

import com.example.k8s.service.FileService;
import com.example.k8s.untils.ApiResponse;
import com.example.k8s.untils.SimpleApiResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

/**
 * Created by zzg on 2018/3/25.
 */
@Controller
@RequestMapping(value = "/bonsai")
@CrossOrigin
public class FileContrller {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

    @Value("${img.location}")
    private String location;

    @Autowired
    private FileService fileService;

    @ResponseBody
    @RequestMapping(value = "/img/upload", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ApiResponse uploadImg(@RequestParam("editormd-image-file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty() || StringUtils.isBlank(multipartFile.getOriginalFilename())) {
            return new SimpleApiResponse(ApiResponse.CODE_NOT_EXIST, "文件不存在");
        }
        String contentType = multipartFile.getContentType();
        if (!contentType.contains("")) {
            return new SimpleApiResponse(ApiResponse.CODE_PARAMETER_ERROR, "文件类型不对");
        }
        String root_fileName = multipartFile.getOriginalFilename();
        System.out.println("root_fileName =========================" + root_fileName);
        logger.info("上传图片:name={},type={}", root_fileName, contentType);
        //处理图片
        Date date = new Date();
        String dateStr = String.valueOf(date.getYear() * 10000 + date.getMonth() * 100 + date.getDate());
        String filePath = location + dateStr;
        logger.info("图片保存路径={}", filePath);
        System.out.println("filePath =========================" + filePath);
        String file_name = null;
        try {
            file_name = fileService.saveImg(multipartFile, filePath);
            if (StringUtils.isNotBlank(file_name)) {
                return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "上传文件成功", file_name);
            }
            return new SimpleApiResponse(ApiResponse.CODE_SUCCESS, "上传文件失败");
        } catch (IOException e) {
            return new SimpleApiResponse(ApiResponse.CODE_OTHER, "其他错误");
        }
    }

}