package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by ASUS on 2017/11/1.
 */
public interface ItemParamService {
    EasyUIDataGridResult listParam(int pages, int rows);
    TaotaoResult getItemParamByCid(Long cid);
    TaotaoResult insertItemParam(Long cid,String paramData);
    TaotaoResult deleteItemParam(String cid);
}
