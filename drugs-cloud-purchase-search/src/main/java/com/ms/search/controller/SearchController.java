package com.ms.search.controller;

import com.ms.common.pojo.PageResult;
import com.ms.search.pojo.Goods;
import com.ms.search.pojo.SearchRequest;
import com.ms.search.pojo.SearchResult;
import com.ms.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2022/1/20 10:51
 */
@Controller
@RequestMapping
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("/page")
    public ResponseEntity<SearchResult> sesrch(@RequestBody SearchRequest request) {
        SearchResult result = this.searchService.search(request);
        if (result == null || CollectionUtils.isEmpty(result.getItems())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
