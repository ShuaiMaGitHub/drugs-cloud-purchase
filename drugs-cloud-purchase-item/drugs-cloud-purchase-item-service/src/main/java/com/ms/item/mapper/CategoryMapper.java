package com.ms.item.mapper;

import com.ms.item.pojo.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.additional.idlist.SelectByIdListMapper;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/14 15:43
 */

public interface CategoryMapper extends Mapper<Category>, SelectByIdListMapper<Category,Long> {
    //根据category_id删除中间表相关数据
    @Delete("DELETE FROM tb_category_brand WHERE category_id = #{cid}")
    void deleteByCategoryIdInCategoryBrand(@Param("cid") Long cid);

   //根据brand_id查询分类信息

    @Select("SELECT * FROM tb_category WHERE id IN (SELECT category_id FROM tb_category_brand WHERE brand_id = #{bid})")
    List<Category> queryByBrandId(@Param("bid") Long bid);
}
