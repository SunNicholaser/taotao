package com.taotao.service;

import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * Created by ASUS on 2017/10/26.
 */
public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long parentId);
}
