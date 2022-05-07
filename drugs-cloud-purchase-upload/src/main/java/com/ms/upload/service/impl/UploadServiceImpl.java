package com.ms.upload.service.impl;

import com.ms.upload.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/16 16:42
 */
@Service
public class UploadServiceImpl implements UploadService {

    //定义媒体类型
    private static final List<String> CONTENT_TYPES = Arrays.asList("image/gif", "image/png", "image/jpeg");
    //private static final List<String> content_types = Arrays.asList("","","");
    //追踪哪个图片出现问题，定义logger日志
    private static final Logger LOGGER = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Override
    public String uploadImage(MultipartFile file) {
        //获取文件名
        String originalFilename = file.getOriginalFilename();

        //校验文件类型
        String contentType = file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)) {
            LOGGER.info("文件类型不合法: {}", originalFilename);
            return null;
        }

        try {
            //校验文件内容
            BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
            if (bufferedImage == null) {
                LOGGER.info("文件内容不合法：{}", originalFilename);
                return null;
            }
            //保存到服务器
            //换环境记得修改服务器路径
            //file.transferTo(new File("F:\\BiShe\\image\\" + originalFilename + generateSuffix()));
            file.transferTo(new File("F:\\BiShe\\image\\" + originalFilename));

            //返回URL，进行回显
            return "http://image.drugs.com/" + originalFilename;
        } catch (IOException e) {
            LOGGER.info("服务器内部错误" + originalFilename);
            e.printStackTrace();
        }
        return null;
    }

    //生成时间戳+随机数
    public static String generateSuffix() {
        // 获得当前时间
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        // 转换为字符串
        String formatDate = format.format(new Date());
        // 随机生成文件编号
        int random = new Random().nextInt(10000);
        return new StringBuffer().append(formatDate).append(
                random).toString();
    }

}
