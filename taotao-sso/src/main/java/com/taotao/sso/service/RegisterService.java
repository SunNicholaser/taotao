package com.taotao.sso.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;

/**
 * Created by ASUS on 2017/11/24.
 */
public interface RegisterService {
    TaotaoResult checkData(String param, int type);
    TaotaoResult register(TbUser user);
}
