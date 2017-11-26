package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

/**
 * Created by Administrator on 2017/10/25 0025.
 */
public interface ItemService {
    TbItem getItemById(Long itemId);
    EasyUIDataGridResult getItemList(int pages, int rows);
    TaotaoResult createItem(TbItem item, String desc,String itemParam); //里面已经包含了事务
    String getItemParamHtml(Long itemId);
}
