package com.ms.item.service.impl;
/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/14 15:45
 */
import com.ms.item.mapper.CategoryMapper;
import com.ms.item.pojo.Category;
import com.ms.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据父节点查询子节点
     *
     * @param pid
     * @return
     */

    @Override
    public List<Category> queryCategoriesByPid(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        List<Category> select = this.categoryMapper.select(record);
        return select;
    }

    @Override
    public List<String> queryNamesByIds(List<Long> ids) {
        List<Category> categories = this.categoryMapper.selectByIdList(ids);
        return categories.stream().map(category -> category.getName()).collect(Collectors.toList());

    }

    /**
     * 增加分类
     * @param category
     * @return
     */
    @Override
    public void saveCategory(Category category) {
        /**
         * 将本节点插入到数据库中
         * 将此节点的父节点的isParent设为true
         */
        //首先设置id为null
        category.setId(null);
        //插入category
        this.categoryMapper.insert(category);
        //修改父节点
        Category parent= new Category();
        parent.setId(category.getParentId());
        parent.setIsParent(true);
        this.categoryMapper.updateByPrimaryKeySelective(parent);

    }

    /**
     * 修改分类
     * @param category
     * @return
     */
    @Override
    public void updateCategory(Category category) {
        this.categoryMapper.updateByPrimaryKeySelective(category);
    }

    /**
     * 根据brand_id查询分类信息
     * @param bid
     * @return
     */
    @Override
    public List<Category> queryByBrandId(Long bid) {
        return this.categoryMapper.queryByBrandId(bid);
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @Override
    public void deleteCategory(Long id) {
        Category category = this.categoryMapper.selectByPrimaryKey(id);
        //判断是否是父节点
        if (category.getIsParent()){
            //查找所有叶子节点
            List<Category> list = new ArrayList<>();
            queryAllLeafNode(category,list);

            //查找所有子节点
            List<Category> list2= new ArrayList<>();
            queryAllNode(category,list2);

            //删除category数据库中的数据，使用list2
            for (Category c : list2) {
                this.categoryMapper.delete(c);
            }

            //维护中间表
            for (Category c : list) {
                this.categoryMapper.deleteByCategoryIdInCategoryBrand(category.getId());
            }
        }else{
            //查询此节点的父亲节点的孩子个数
            Example example= new Example(Category.class);
            example.createCriteria().andEqualTo("parentId",category.getParentId());
            List<Category> list = this.categoryMapper.selectByExample(example);
            if(list.size()!=1){
                //有兄弟，直接删除自己
                this.categoryMapper.deleteByPrimaryKey(category.getId());
                //维护中间表
                this.categoryMapper.deleteByCategoryIdInCategoryBrand(category.getId());
            }else {
                //已经没有兄弟
                this.categoryMapper.deleteByPrimaryKey(category.getId());
                Category parent= new Category();
                parent.setId(category.getParentId());
                parent.setIsParent(false);
                this.categoryMapper.updateByPrimaryKeySelective(parent);

                //维护中间表
                this.categoryMapper.deleteByCategoryIdInCategoryBrand(category.getId());
            }
        }

    }

    /**
     * 查询本节点下所有子节点
     * @param category
     * @param node
     */
    private void queryAllNode(Category category, List<Category> node) {
        node.add(category);
        Example example= new Example(Category.class);
        example.createCriteria().andEqualTo("parentId",category.getId());
        List<Category> list = this.categoryMapper.selectByExample(example);
        for (Category category1 : list) {
            queryAllNode(category1,node);
        }
    }


    /**
     * 查询本节点下所包含的所有叶子节点，用于维护tb_category_brand中间表
     * @param category
     * @param leafNode
     */
    private void queryAllLeafNode(Category category, List<Category> leafNode) {
        if(!category.getIsParent()){
            leafNode.add(category);
        }
        Example example= new Example(Category.class);
        example.createCriteria().andEqualTo("parentId",category.getId());
        List<Category> list = this.categoryMapper.selectByExample(example);

        for (Category category1 : list) {
            queryAllLeafNode(category1,leafNode);

        }
    }



}
