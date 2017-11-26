package com.taotao.controller;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemParamService;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ASUS on 2017/10/26.
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemParamService itemParamService;
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    private TbItem getItemById(@PathVariable Long itemId) {
        TbItem item = itemService.getItemById(itemId);
        return item;
    }
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page,Integer rows){
        EasyUIDataGridResult result = itemService.getItemList(page, rows);
        return result;
    }
    @RequestMapping(value = "/item/save",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createItem(TbItem item,String desc,String itemParams){
        TaotaoResult result = itemService.createItem(item, desc,itemParams);
        return result;
    }
    //商品模板展示
    @RequestMapping("/item/param/list")
    @ResponseBody
    public EasyUIDataGridResult getItemParamList(@RequestParam(defaultValue = "1") Integer page , @RequestParam(defaultValue = "30") Integer rows){
        EasyUIDataGridResult result = itemParamService.listParam(page,rows);
        return result;
    }
    @RequestMapping("/page/item/{itemId}")
    public String showItemParam(@PathVariable Long itemId, Model model){
        String html = itemService.getItemParamHtml(itemId);
        model.addAttribute("html",html);
        return "itemparam";
    }
}

