package com.ms.search.repository;

import com.ms.search.pojo.Goods;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author MaShuai
 * @version 1.0
 * @date 2022/1/18 9:54
 */
public interface GoodsRepository extends ElasticsearchRepository<Goods,Long> {
}
