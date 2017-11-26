package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ASUS on 2017/11/1.
 * 商品规格参数模板管理
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;
    @RequestMapping("/cid/{cid}")
    @ResponseBody
    public TaotaoResult getItemCatByCid1(@PathVariable Long cid){
        TaotaoResult itemParamByCid = itemParamService.getItemParamByCid(cid);
        return itemParamByCid;
    }
    @RequestMapping("/query/itemcatid/{cid}")
    @ResponseBody
    public TaotaoResult getItemCatByCid(@PathVariable Long cid){
        TaotaoResult itemParamByCid = itemParamService.getItemParamByCid(cid);
        return itemParamByCid;
    }
    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable Long cid,String paramData){
        TaotaoResult taotaoResult = itemParamService.insertItemParam(cid, paramData);
        return taotaoResult;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult deleteItemParam( @RequestParam("ids") String ids){
        TaotaoResult result = itemParamService.deleteItemParam(ids);
        return result;
    }
}
