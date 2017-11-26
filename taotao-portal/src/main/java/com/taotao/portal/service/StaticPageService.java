package com.taotao.portal.service;

import com.taotao.common.pojo.TaotaoResult;

/**
 * Created by ASUS on 2017/11/24.
 */
public interface StaticPageService {
    TaotaoResult getItemHtml(Long itemId) throws Exception;
}
