package com.ms.item.mapper;

import com.ms.item.pojo.Brand;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2021/12/14 17:43
 */
public interface BrandMapper extends Mapper<Brand> {
    //新增商品分类和品牌中间表
    @Insert("insert into tb_category_brand (category_id, brand_id) value(#{cid}, #{bid})")
    void inserCategoryAndBrand(@Param("cid") Long cid,@Param("bid") Long bid);

    //根据brand_id删除中间表相关数据
    @Delete("DELETE FROM tb_category_brand WHERE brand_id = #{bid}")
    void deleteByBrandIdInCategoryBrand(Long bid);

    @Select("SELECT * FROM tb_brand a INNER JOIN tb_category_brand b on a.id=b.brand_id where b.category_id=#{cid}")
    List<Brand> selectBrandByCid(Long cid);
}
