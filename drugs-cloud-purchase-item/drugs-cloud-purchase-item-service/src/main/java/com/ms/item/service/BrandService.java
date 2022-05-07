package com.ms.item.service;

import com.ms.common.pojo.PageResult;
import com.ms.item.pojo.Brand;

import java.util.List;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/14 17:44
 */
public interface BrandService {

    PageResult<Brand> queryBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc);

    void saveBrand(Brand brand, List<Long> cids);

    void deleteBrand(Long id);

    void updateBrand(List<Long> cids, Brand brand);

    List<Brand> queryBrandByCid(Long cid);

    Brand queryBrandById(Long id);
}
