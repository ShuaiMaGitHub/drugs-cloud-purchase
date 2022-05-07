package com.ms.item.service;

import com.ms.common.pojo.PageResult;
import com.ms.item.bo.SpuBo;
import com.ms.item.pojo.Sku;
import com.ms.item.pojo.Spu;
import com.ms.item.pojo.SpuDetail;

import java.util.List;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/17 15:39
 */
public interface GoodsService {

    PageResult<SpuBo> querySpuByPage(String key, Boolean saleable, Integer page, Integer rows);

    void saveGoods(SpuBo spuBo);

    SpuDetail querySpuDetailBySpuId(Long spuId);

    List<Sku> querySkuBySpuId(Long spuId);

    void updateGoods(SpuBo spuBo);

    void deleteGoods(String id);

    void goodsSoldOut(Long id);

    Spu querySpuById(Long id);

    Sku querySkuBySkuId(Long skuId);
}
