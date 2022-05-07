package com.ms.search.service;

import com.ms.common.pojo.PageResult;
import com.ms.item.pojo.Spu;
import com.ms.search.pojo.Goods;
import com.ms.search.pojo.SearchRequest;
import com.ms.search.pojo.SearchResult;

import java.io.IOException;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2022/1/17 16:35
 */
public interface SearchService {

    Goods buildGoods(Spu spu) throws IOException;

    SearchResult search(SearchRequest request);
}
