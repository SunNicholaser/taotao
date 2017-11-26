package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;

/**
 * Created by ASUS on 2017/11/2.
 */
public interface ContentCatgoryService {
    List<EasyUITreeNode> getContentCatList(Long parentId);
    TaotaoResult insertCatgory(Long parentId ,String name);
}
