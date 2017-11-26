package com.taotao.portal.service.impl;

import com.taotao.common.pojo.PictureResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import com.taotao.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ASUS on 2017/11/14.
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;
    @Override
    public SearchResult search(String keyword, int page, int rows) {
        Map<String,String> param = new HashMap<>();
        param.put("keyword",keyword);
        param.put("page",page+"");
        param.put("rows",rows+"");
        //调用服务
        String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
        //转化成java对象
        TaotaoResult result = TaotaoResult.formatToPojo(json, SearchResult.class);
        //去返回结果
        SearchResult searchResult = (SearchResult) result.getData();
        return searchResult;
    }
}
