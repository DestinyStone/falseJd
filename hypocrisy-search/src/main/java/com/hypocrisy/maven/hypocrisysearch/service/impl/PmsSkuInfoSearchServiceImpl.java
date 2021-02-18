package com.hypocrisy.maven.hypocrisysearch.service.impl;

import bean.PmsBaseAttrInfo;
import bean.PmsBaseAttrValue;
import bean.PmsSkuAttrValue;
import com.alibaba.fastjson.JSONObject;
import com.hypocrisy.maven.hypocrisysearch.service.PmsSkuInfoSearchService;
import document.PmsSkuInfoSearch;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import response.Message;
import response.type.ResponseCodeType;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Auther: DestinyStone
 * @Date: 2020/11/10 22:03
 * @Description:
 */
@Service
public class PmsSkuInfoSearchServiceImpl implements PmsSkuInfoSearchService {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public Message selectByCondition(String keyword, String[] valueIds, String catalog3Id, String currentPage, String size,  String priceGte, String priceLte, String sort) {
        // 构建查询条件
        NativeSearchQuery nativeSearchQuery = searchQueryBuilder(keyword, valueIds, catalog3Id, currentPage, size, priceGte, priceLte, sort);

        // 获取查询结果
        Map<String, Object> mapResult = getSearchResult(nativeSearchQuery, PmsSkuInfoSearch.class);

        List<PmsSkuInfoSearch> pmsSkuInfoSearchList =  (List<PmsSkuInfoSearch>)mapResult.get("pmsSkuInfoSearchList");
        Long total = (Long)mapResult.get("total");

        // 处理结果集中的Attr
        List<PmsSkuAttrValue> pmsSkuAttrValueList = new ArrayList<>();
        pmsSkuInfoSearchList.stream().forEach(x -> pmsSkuAttrValueList.addAll(x.getPmsSkuAttrValueList()));

        // 过滤掉重复的
        List<PmsSkuAttrValue> pmsSkuAttrValueListResult = pmsSkuAttrValueList.stream().distinct().collect(Collectors.toList());

        if (valueIds !=null && valueIds.length > 0) {
            // 获取所有应该删除的AttrId
            String[] attrIds = pmsSkuAttrValueListResult.stream().filter(pmsSkuAttrValue -> {
                // 如果 ValueId存在于 valueIds 中 则过滤出来
                return Arrays.stream(valueIds).anyMatch(valueId -> valueId.equals(pmsSkuAttrValue.getValueId()));
            }).map(x -> x.getAttrId()).toArray(String[]::new);
            // 删除掉存在attrId的
            pmsSkuAttrValueListResult = pmsSkuAttrValueListResult.stream().filter(pmsSkuAttrValue -> {
                return !Arrays.stream(attrIds).anyMatch(attrId -> attrId.equals(pmsSkuAttrValue.getAttrId()));
            }).collect(Collectors.toList());
        }


        // 封装成pmsBaseAttrInfo 对象
        List<PmsBaseAttrInfo> pmsBaseAttrInfoList = new ArrayList<>();
        Map<String, List<PmsSkuAttrValue>> collect = pmsSkuAttrValueListResult.stream().collect(Collectors.groupingBy(PmsSkuAttrValue::getAttrId));
        Iterator<String> iterator = collect.keySet().iterator();
        while (iterator.hasNext()) {
            String attrId = iterator.next();
            String attrName = pmsSkuAttrValueListResult.stream().filter(x -> x.getAttrId().equals(attrId)).findFirst().get().getAttrName();
            PmsBaseAttrInfo pmsBaseAttrInfo = new PmsBaseAttrInfo();
            pmsBaseAttrInfo.setAttrName(attrName);

            List<PmsBaseAttrValue> pmsBaseAttrValueListResult = collect.get(attrId).stream().map(x -> {
                PmsBaseAttrValue pmsBaseAttrValue = new PmsBaseAttrValue();
                pmsBaseAttrValue.setId(x.getValueId());
                pmsBaseAttrValue.setAttrId(x.getAttrId());
                pmsBaseAttrValue.setValueName(x.getValueName());
                return pmsBaseAttrValue;
            }).collect(Collectors.toList());
            pmsBaseAttrInfo.setPmsBaseAttrValueList(pmsBaseAttrValueListResult);
            pmsBaseAttrInfoList.add(pmsBaseAttrInfo);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("pmsSkuInfoSearchList", pmsSkuInfoSearchList);
        jsonObject.put("pmsBaseAttrInfoList", pmsBaseAttrInfoList);
        jsonObject.put("total", total);
        return new Message(ResponseCodeType.SUCCESS, jsonObject, true);
    }

    /**
     * 构建查询条件
     * @param keyword 查询关键字
     * @param valueIds attrValueId
     * @param catalog3Id 三级分类Id
     * @param currentPage 当前页数
     * @param size 一页放回最大数量
     * @param priceGte 大于 价格
     * @param priceLte 小于 价格
     * @param sort 排序规则 0 -> 综合,  1->销量,  2 -> 评价, 3 -> 价格(升序)
     * @return
     */
    private NativeSearchQuery searchQueryBuilder(String keyword, String[] valueIds, String catalog3Id, String currentPage, String size, String priceGte, String priceLte, String sort) {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (valueIds != null && valueIds.length > 0) {
            for (String valueId : valueIds) {
                boolQueryBuilder.filter(QueryBuilders.termQuery("pmsSkuAttrValueList.valueId" ,valueId));
            }
        }

        if (!StringUtils.isBlank(keyword)) {
            boolQueryBuilder.must(QueryBuilders.multiMatchQuery(keyword, "skuName", "skuDesc"));
            // 设置查询高亮
            HighlightBuilder highlightBuilder = new HighlightBuilder().field("skuName").field("skuDesc")
                    .preTags("<font class='font-color'>").postTags("</font>")
                    .numOfFragments(1).requireFieldMatch(false);
            nativeSearchQueryBuilder.withHighlightBuilder(highlightBuilder);
        }

        if (!StringUtils.isBlank(catalog3Id)) {
            boolQueryBuilder.must(QueryBuilders.termQuery("catalog3Id", catalog3Id));
        }

        // 构建分页
        if (!StringUtils.isBlank(currentPage)&&!StringUtils.isBlank(size)) {
            nativeSearchQueryBuilder.withPageable(PageRequest.of(new Integer(currentPage) - 1, new Integer(size)));
        }

        // 构建价格区间
        if (!StringUtils.isBlank(priceGte) || !StringUtils.isBlank(priceLte)) {
            if (!StringUtils.isBlank(priceGte) && !StringUtils.isBlank(priceLte))
                boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gte(priceGte).lte(priceLte));
            if (!StringUtils.isBlank(priceGte))
                boolQueryBuilder.must(QueryBuilders.rangeQuery("price").gte(priceGte));
            if (!StringUtils.isBlank(priceLte))
                boolQueryBuilder.must(QueryBuilders.rangeQuery("price").lte(priceLte));
        }

        // 构建排序规则
        if (!StringUtils.isBlank(sort)) {
            if ("3".equals(sort)) {
                nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.ASC));
            }
        }

        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.withQuery(boolQueryBuilder).build();
        return nativeSearchQuery;
    }

    /**
     * 构建查询条件
     * @param nativeSearchQuery 查询语句
     * @param clazz 返回的类
     * @return
     */
    private Map<String, Object> getSearchResult( NativeSearchQuery nativeSearchQuery, Class clazz) {

        Map<String, Object> mapResult = new HashMap<>();

        List<PmsSkuInfoSearch> pmsSkuInfoSearchList = new ArrayList<>();
        elasticsearchTemplate.queryForPage(nativeSearchQuery, clazz, new SearchResultMapper() {
            @Override
            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
                mapResult.put("total",  response.getHits().totalHits);
                response.getHits().forEach(hit -> {
                    PmsSkuInfoSearch pmsSkuInfoSearch = JSONObject.parseObject(hit.getSourceAsString(), PmsSkuInfoSearch.class);
                    Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                    Iterator<String> iterator = highlightFields.keySet().iterator();
                    while(iterator.hasNext()) {
                        String field = iterator.next();
                        // 获取高亮结果集
                        String value = highlightFields.get(field).getFragments()[0].string();
                        // 调用反射 设置高亮字段
                        try {
                            Field declaredField = clazz.getDeclaredField(field);
                            declaredField.setAccessible(true);
                            declaredField.set(pmsSkuInfoSearch, value);
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    pmsSkuInfoSearchList.add(pmsSkuInfoSearch);
                });

                return null;
            }

            @Override
            public <T> T mapSearchHit(SearchHit searchHit, Class<T> type) { return null; }
        });
        mapResult.put("pmsSkuInfoSearchList", pmsSkuInfoSearchList);
        return mapResult;
    }
}
