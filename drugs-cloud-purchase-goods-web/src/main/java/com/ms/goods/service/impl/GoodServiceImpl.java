package com.ms.goods.service.impl;

import com.ms.goods.client.BrandClient;
import com.ms.goods.client.CategoryClient;
import com.ms.goods.client.GoodsClient;
import com.ms.goods.client.SpecificationClient;
import com.ms.goods.service.GoodsService;
import com.ms.item.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Shuai.Ma
 * @date 2022/3/17 15:14
 */
@Service
public class GoodServiceImpl implements GoodsService {

    @Autowired
    private BrandClient brandClient;

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SpecificationClient specificationClient;

    @Override
    public Map<String, Object> loadData(Long spuId) {
        Map<String,Object> model=new HashMap<>();
        //根据spuId查询spu
        Spu spu = this.goodsClient.querySpuById(spuId);
        //查询spuDetail
        SpuDetail spuDetail = this.goodsClient.querySpuDetailBySpuId(spuId);
        //查询分类
        List<Long> cids = Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3());
        List<String> names = this.categoryClient.queryNameByIds(cids);
        //初始化一个分类map
        List<Map<String,Object>> categories=new ArrayList<>();
        for (int i = 0; i <cids.size() ; i++) {
            Map<String,Object> map=new HashMap<>();
            map.put("id",cids.get(i));
            map.put("name",names.get(i));
            categories.add(map);
        }
        //查询品牌
        Brand brand = this.brandClient.queryBrandById(spu.getBrandId());
        //skus
        List<Sku> skus = this.goodsClient.querySkuBySpuId(spuId);
        //查询规格参数组
        List<SpecGroup> groups = this.specificationClient.queryGroupWithParam(spu.getCid3());
        //查询特殊的规格参数
        List<SpecParam> params = this.specificationClient.queryParams(null, spu.getCid3(), false, null);
        //初始化特殊规格参数的map
        Map<Long,String> paramMap=new HashMap<>();
        params.forEach(param->{
            paramMap.put(param.getId(),param.getName());
        });

        model.put("spu",spu);
        model.put("spuDetail",spuDetail);
        model.put("categories",categories);
        model.put("brand",brand);
        model.put("skus",skus);
        model.put("groups",groups);
        model.put("paramMap",paramMap);
        return model;
    }
}
