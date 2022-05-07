package com.ms.goods.controller;

import com.ms.goods.service.GoodsHtmlService;
import com.ms.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

/**
 * @author Shuai.Ma
 * @date 2022/3/17 11:22
 */
@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsHtmlService goodsHtmlService;

    @GetMapping("item/{id}.html")
    public String toItemPage(@PathVariable("id") Long id , Model model){
        Map<String, Object> map = this.goodsService.loadData(id);
        model.addAllAttributes(map);
        this.goodsHtmlService.createHtml(id);
        return "item";
    }

}
