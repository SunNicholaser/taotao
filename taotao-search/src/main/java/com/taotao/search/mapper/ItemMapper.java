package com.taotao.search.mapper;

import com.taotao.search.pojo.SearchItem;

import java.util.List;

/**
 * Created by ASUS on 2017/11/14.
 */
public interface ItemMapper {
    List<SearchItem> getItemList() throws Exception ;
}
