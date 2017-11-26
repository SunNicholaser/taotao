package com.taotao.order.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.order.pojo.OrderInfo;

/**
 * Created by ASUS on 2017/11/25.
 */
public interface OrderService {
    TaotaoResult createOrder(OrderInfo orderInfo);
}
