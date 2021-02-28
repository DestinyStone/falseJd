package com.hypocrisy.maven.hypocrisysearch;

import bean.PmsSkuInfo;
import com.alibaba.fastjson.JSONObject;
import com.hypocrisy.maven.hypocrisysearch.repository.PmsSkuInfoRepository;
import document.PmsSkuInfoSearch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HypocrisySearchApplicationTests {

    @Autowired
    private PmsSkuInfoRepository pmsSkuInfoRepository;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    void test() {
        elasticsearchTemplate.createIndex(PmsSkuInfoSearch.class);
        elasticsearchTemplate.putMapping(PmsSkuInfoSearch.class);
    }

    @Test
    void contextLoads() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("D://1.txt");
        byte[] buff = new byte[fileInputStream.available()];
        fileInputStream.read(buff);
        List<PmsSkuInfo> pmsSkuInfoList = JSONObject.parseArray(new String(buff), PmsSkuInfo.class);

        System.out.println(pmsSkuInfoList.size());
        List<PmsSkuInfoSearch> pmsSkuInfoSearches = new ArrayList<>();
        pmsSkuInfoList.forEach(pmsSkuInfo -> {
            PmsSkuInfoSearch pmsSkuInfoSearch = new PmsSkuInfoSearch();
            BeanUtils.copyProperties(pmsSkuInfo, pmsSkuInfoSearch);
            pmsSkuInfoSearches.add(pmsSkuInfoSearch);
        });
        pmsSkuInfoRepository.saveAll(pmsSkuInfoSearches);
    }
}
