package com.taotao.portal.service;

import com.taotao.pojo.TbItem;

/**
 * Created by ASUS on 2017/11/18.
 */
public interface ItemService {
    TbItem getItemById(Long itemId);
    String getItemDescById(Long itemId);
    String getItemParamById(Long itemId);
}
