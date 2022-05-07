package com.ms.item.api;

import com.ms.common.pojo.PageResult;
import com.ms.item.bo.SpuBo;
import com.ms.item.pojo.Sku;
import com.ms.item.pojo.Spu;
import com.ms.item.pojo.SpuDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/17 15:41
 */

public interface GoodsApi {

    /**
     * 根据条件分页查询spu
     *
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/spu/page")
    PageResult<SpuBo> querySpuByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows
    );


    /**
     * 根据spuId查询spuDetail
     *
     * @param spuId
     * @return
     */
    @GetMapping("/spu/detail/{spuId}")
    SpuDetail querySpuDetailBySpuId(@PathVariable("spuId") Long spuId);


    /**
     * 根据skuId查询sku集合
     *
     * @param spuId
     * @return
     */
    @GetMapping("/sku/list")
    List<Sku> querySkuBySpuId(@RequestParam("id") Long spuId);

    /**
     * 根据spuId查询spu
     * @param id
     * @return
     */
    @GetMapping("{id}")
    Spu querySpuById(@PathVariable("id") Long id);

    /**
     * 根据skuId查询sku
     * @param skuId
     * @return
     */
    @GetMapping("sku/{skuId}")
    Sku querySkuBySkuId(@PathVariable("skuId")Long skuId);
}
