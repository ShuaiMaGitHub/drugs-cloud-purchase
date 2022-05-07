package com.ms.search.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2022/1/20 10:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {

    /**
     * 搜索条件
     */
    private String key;
    /**
     * 当前页
     */
    private Integer page;

    private Map<String,Object> filter;

    /**
     * 每页大小，不从页面接收，而是固定大小
     */
    private static final int DEFAULT_SIZE = 20;
    /**
     * 默认页
     */
    private static final int DEFAULT_PAGE = 1;

    public Integer getPage() {
        if(page == null){
            return DEFAULT_PAGE;
        }
        // 获取页码时做一些校验，不能小于1
        return Math.max(DEFAULT_PAGE, page);
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return DEFAULT_SIZE;
    }

}
