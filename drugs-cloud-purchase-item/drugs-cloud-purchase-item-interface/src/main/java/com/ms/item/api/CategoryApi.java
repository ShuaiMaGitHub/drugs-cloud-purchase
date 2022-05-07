package com.ms.item.api;

import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分类管理
 *
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/14 14：29
 */


@RequestMapping("/category")
public interface CategoryApi {


    @GetMapping
    List<String> queryNameByIds(@RequestParam("ids") List<Long> ids);
}
