package com.taotao.controller;

import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;
import com.taotao.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ASUS on 2017/10/31.
 * 图片上传controller
 */
@Controller
public class PictureController {
    @Autowired
    private PictureService pictureService;
    @RequestMapping("/pic/upload")
    @ResponseBody
    public String uploadFile(MultipartFile uploadFile){
        PictureResult result = pictureService.uploadPic(uploadFile);
        //x需要把java对象手动转换成json
        String s = JsonUtils.objectToJson(result);
        return s;
    }
}
