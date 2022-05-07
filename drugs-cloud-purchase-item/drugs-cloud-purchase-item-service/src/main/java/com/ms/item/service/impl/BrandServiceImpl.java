package com.ms.item.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ms.common.pojo.PageResult;
import com.ms.item.mapper.BrandMapper;
import com.ms.item.pojo.Brand;
import com.ms.item.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/14 17:45
 */
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 根据查询条件分页并排序查询品牌信息
     *
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @Override
    public PageResult<Brand> queryBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        //初始化example对象
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();

        //根据name模糊查询，或者根据首字母查询
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }

        //添加分页条件
        PageHelper.startPage(page, rows);

        //添加排序条件
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }

        List<Brand> brands = this.brandMapper.selectByExample(example);
        //包装成pageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        //包装成分页结果集返回
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 新增品牌
     *
     * @param brand
     * @param cids
     * @return
     */
    @Override
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {

        //先新增brand
        this.brandMapper.insertSelective(brand);

        //再新增中间表
        cids.forEach(cid -> {
            this.brandMapper.inserCategoryAndBrand(cid, brand.getId());
        });
    }

    /**
     * 更新品牌
     *
     * @param cids
     * @param brand
     * @return
     */
    @Override
    @Transactional
    public void updateBrand(List<Long> cids, Brand brand) {
        //先更新Brand
        this.brandMapper.updateByPrimaryKey(brand);
        //通过品牌id删除中间表
        brandMapper.deleteByBrandIdInCategoryBrand(brand.getId());
        //再新增中间表
        for (Long cid : cids) {
            brandMapper.inserCategoryAndBrand(cid, brand.getId());
        }
    }

    /**
     * 根据分类id查询品牌列表
     * @param cid
     * @return
     */
    @Override
    public List<Brand> queryBrandByCid(Long cid) {
        return this.brandMapper.selectBrandByCid(cid);
    }

    @Override
    public Brand queryBrandById(Long id) {
        return this.brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 删除品牌
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBrand(Long id) {
        //删除品牌
        this.brandMapper.deleteByPrimaryKey(id);

        //维护中间表
        this.brandMapper.deleteByBrandIdInCategoryBrand(id);
    }



}
