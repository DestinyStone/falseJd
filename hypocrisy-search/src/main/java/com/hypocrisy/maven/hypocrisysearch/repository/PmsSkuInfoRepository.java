package com.hypocrisy.maven.hypocrisysearch.repository;

import document.PmsSkuInfoSearch;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: DestinyStone
 * @Date: 2020/11/10 20:46
 * @Description:
 */
@Repository
public interface PmsSkuInfoRepository extends ElasticsearchRepository<PmsSkuInfoSearch, String> {
}
