package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

/**
 * Created by ASUS on 2017/11/18.
 */
public class PortalItem extends TbItem {
    public String[] getImages(){
        if(this.getImage()!=null && !this.getImage().equals("")){
            String[] imgs = this.getImage().split(",");
            return imgs;
        }
        return null;
    }
}
