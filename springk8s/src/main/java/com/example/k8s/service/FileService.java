package com.example.k8s.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by zzg on 2018/3/25.
 */
public interface FileService {

   String saveImg(MultipartFile multipartFile, String path) throws IOException;
}
