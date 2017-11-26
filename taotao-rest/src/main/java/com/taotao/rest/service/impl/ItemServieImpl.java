package com.taotao.rest.service.impl;

import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.rest.component.JedisClient;
import com.taotao.rest.service.ItemService;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ASUS on 2017/11/15.
 */
@Service
public class ItemServieImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${REDIS_ITEM_KEY}")
    private String REDIS_ITEM_KEY;
    @Value("${ITEM_BASE_INFO_KEY}")
    private String ITEM_BASE_INFO_KEY;
    @Value("${ITEM_EXPIRE_SECOND}")
    private Integer ITEM_EXPIRE_SECOND;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Value("${ITEM_DESC_KEY}")
    private String ITEM_DESC_KEY;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
    @Value("${ITEM_PARAM_KEY}")
    private String ITEM_PARAM_KEY;
    @Override
    public TbItem getItemById(Long itemId) {
        //查询缓存，如果有缓存，直接返回
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":" +ITEM_BASE_INFO_KEY );
            //判断数据是否存在
            if(StringUtils.isNotBlank(json)){
                //把json数据转换成java对象
                TbItem item = JsonUtils.jsonToPojo(json, TbItem.class);
                return item;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbItem item = itemMapper.selectByPrimaryKey(itemId);

        //向redis中添加缓存
        //添加缓存原则，不能影响正常业务逻辑
        jedisClient.set(REDIS_ITEM_KEY + ":" + itemId + ":" +ITEM_BASE_INFO_KEY , JsonUtils.objectToJson(item));
       //设置key的过期时间
        jedisClient.expire(REDIS_ITEM_KEY + ":" + itemId + ":" +ITEM_BASE_INFO_KEY , ITEM_EXPIRE_SECOND);
        return item;
    }

    /**
     * 查询商品详情
     * @param itemId
     * @return
     */
    @Override
    public TbItemDesc getItemDescById(Long itemId) {
        //查询缓存
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_DESC_KEY);
            if(StringUtils.isNotBlank(json)){
                TbItemDesc desc = JsonUtils.jsonToPojo(json,TbItemDesc.class);
                return desc;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);

        //添加缓存
        try {
            jedisClient.set(REDIS_ITEM_KEY+":"+itemId +":"+ITEM_DESC_KEY,JsonUtils.objectToJson(itemDesc));
            //设置过期时间
            jedisClient.expire(REDIS_ITEM_KEY+":"+itemId +":"+ITEM_DESC_KEY,ITEM_EXPIRE_SECOND);
        }catch (Exception e){
            e.printStackTrace();
        }
        return itemDesc;
    }

    @Override
    public TbItemParamItem getItemParamById(Long itemId) {
        //添加缓存逻辑
        //查询缓存
        try {
            String json = jedisClient.get(REDIS_ITEM_KEY + ":" + itemId + ":" + ITEM_PARAM_KEY);
            if(StringUtils.isNotBlank(json)){
                TbItemParamItem desc = JsonUtils.jsonToPojo(json,TbItemParamItem.class);
                return desc;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //根据商品Id查询规格参数
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
        //取规格参数
        if(list!=null && list.size()>0){
            TbItemParamItem itemParamItem = list.get(0);
            //添加缓存
            try {
                jedisClient.set(REDIS_ITEM_KEY+":"+itemId +":"+ITEM_PARAM_KEY,JsonUtils.objectToJson(itemParamItem));
                //设置过期时间
                jedisClient.expire(REDIS_ITEM_KEY+":"+itemId +":"+ITEM_PARAM_KEY,ITEM_EXPIRE_SECOND);
            }catch (Exception e){
                e.printStackTrace();
            }
            return itemParamItem;
        }
        return null;
    }
}
