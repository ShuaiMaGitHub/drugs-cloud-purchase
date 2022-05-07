package com.ms.upload.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/16 16:41
 */
public interface UploadService {
    String uploadImage(MultipartFile file);
}
