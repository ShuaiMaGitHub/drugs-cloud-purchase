package com.ms.goods.service.impl;

import com.ms.goods.service.GoodsHtmlService;
import com.ms.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


/**
 * @author Shuai.Ma
 * @date 2022/3/19 11:28
 */
@Service
public class GoodsHtmlServiceImpl implements GoodsHtmlService {

    @Autowired
    private TemplateEngine engine;

    @Autowired
    private GoodsService goodsService;

    /**
     * 创建html页面
     *
     * @param spuId
     * @throws Exception
     */
    @Override
    public void createHtml(Long spuId) {

        //初始化运行上下文
        Context context = new Context();
        //设置数据模型
        context.setVariables(this.goodsService.loadData(spuId));

        PrintWriter printWriter = null;
        try {
            //把静态文件生成到服务器本地
            File file = new File("D:\\nginx\\nginx-1.14.2\\html\\item\\" + spuId + ".html");
            printWriter = new PrintWriter(file);

            this.engine.process("item",context,printWriter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(printWriter != null){
                printWriter.close();
            }
        }
    }

    /**
     * 删除html页面
     * @param id
     */
    public void deleteHtml(Long id) {
        File file = new File("D:\\nginx\\nginx-1.14.2\\html\\item\\" + id + ".html");
        file.deleteOnExit();
    }

}
