package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2017/11/1.
 * 商品规格参数模板管理service
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper tbItemParamMapper;
    @Override
    public EasyUIDataGridResult listParam(int pages, int rows) {
        PageHelper.startPage(pages,rows);
        TbItemParamExample example = new TbItemParamExample();
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(example);
        PageInfo<TbItemParam> info = new PageInfo<TbItemParam>(tbItemParams);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setTotal(info.getTotal());
        result.setRows(tbItemParams);
        return result;

    }

    @Override
    public TaotaoResult getItemParamByCid(Long cid) {
        //根据cid查询规格参数模板
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        //执行查询
        List<TbItemParam> tbItemParams = tbItemParamMapper.selectByExampleWithBLOBs(example);
        //判断是否查询到结果
        if(tbItemParams!=null && tbItemParams.size()>0){
            TbItemParam tbItemParam = tbItemParams.get(0);
            return TaotaoResult.ok(tbItemParam);
        }
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertItemParam(Long cid, String paramData) {
       //创建一个pojo
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        //插入记录
        tbItemParamMapper.insert(itemParam);

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteItemParam(String cid) {
        Long id = (long) Integer.parseInt(cid);
        TbItemParamExample itemParamExample = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = itemParamExample.createCriteria();
        criteria.andIdEqualTo(id);
        tbItemParamMapper.deleteByExample(itemParamExample);
        return TaotaoResult.ok();
    }
}
