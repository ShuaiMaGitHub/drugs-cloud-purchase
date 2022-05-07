package com.ms.search.pojo;

import com.ms.common.pojo.PageResult;
import com.ms.item.pojo.Brand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author Shuai.Ma
 * @date 2022/3/7 17:58
 */

@Data
@NoArgsConstructor
public class SearchResult extends PageResult<Goods> {

    private List<Map<String,Object>> categories;

    private List<Brand> brand;

    private List<Map<String,Object>> specs;

    public SearchResult(List<Map<String, Object>> categories, List<Brand> brand, List<Map<String, Object>> specs) {
        this.categories = categories;
        this.brand = brand;
        this.specs = specs;
    }

    public SearchResult(Long total, List<Goods> items, List<Map<String, Object>> categories, List<Brand> brand, List<Map<String, Object>> specs) {
        super(total, items);
        this.categories = categories;
        this.brand = brand;
        this.specs = specs;
    }

    public SearchResult(Long total, Integer totalPage, List<Goods> items, List<Map<String, Object>> categories, List<Brand> brand, List<Map<String, Object>> specs) {
        super(total, totalPage, items);
        this.categories = categories;
        this.brand = brand;
        this.specs = specs;
    }
}
