package com.ms.item.api;

import com.ms.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 品牌管理
 *
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/15 9:26
 */

@RequestMapping("/brand")
public interface BrandApi {

    @GetMapping("{id}")
    Brand queryBrandById(@PathVariable("id") Long id);

}
