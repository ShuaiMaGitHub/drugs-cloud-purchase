package com.ms.upload.controller;

import com.ms.upload.service.UploadService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/16 16:33
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private UploadService uploadService;

    /**
     * 图片上传
     * @param file
     * @return
     */
    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String url = uploadService.uploadImage(file);
        //如果文件为空，返回错误
        if (StringUtils.isBlank(url)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(url);

    }
}
