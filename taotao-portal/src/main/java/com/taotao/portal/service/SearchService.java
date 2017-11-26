package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * Created by ASUS on 2017/11/14.
 */
public interface SearchService {
    SearchResult search(String keyword,int page,int rows);
}
