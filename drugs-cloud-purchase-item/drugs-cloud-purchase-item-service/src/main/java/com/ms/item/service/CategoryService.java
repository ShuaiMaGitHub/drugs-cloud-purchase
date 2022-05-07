package com.ms.item.service;

import com.ms.item.pojo.Category;

import java.util.List;
/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/14 15:44
 */
public interface CategoryService {

    List<Category> queryCategoriesByPid(Long pid);

    List<String> queryNamesByIds(List<Long> ids);

    void deleteCategory(Long id);


    void saveCategory(Category category);

    void updateCategory(Category category);

    List<Category> queryByBrandId(Long bid);
}
