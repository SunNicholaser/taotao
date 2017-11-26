package com.taotao.service.impl;

import com.taotao.common.pojo.PictureResult;
import com.taotao.service.PictureService;
import com.taotao.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by ASUS on 2017/10/31.
 * 图片上传service
 */
@Service
public class PictureServiceImpl implements PictureService {
    @Value("${IMAGE_SERVER_BASE_URL}")
    private String IMAGE_SERVER_BASE_URL;
    @Override
    public PictureResult uploadPic(MultipartFile picFile) {
        PictureResult result = new PictureResult();
        //判断图片是否为空
        if(picFile.isEmpty()){
            result.setError(1);
            result.setMessage("图片为空");
            return result;
        }
        //上传到图片服务器
        //取图片扩展名`
        String originFilename = picFile.getOriginalFilename();
        String extName = originFilename.substring(originFilename.lastIndexOf(".") + 1);
        FastDFSClient client = new FastDFSClient("D:\\github\\taotao\\taotao-manager-web\\src\\main\\resources\\properties\\client.conf");
        try {
            String url = client.upLoadFile(picFile.getBytes());
            //拼接图片服务器的ip地址
            url = IMAGE_SERVER_BASE_URL+url;
            //把url响应给客户端
            result.setError(0);
            result.setUrl(url);
        } catch (IOException e) {

            e.printStackTrace();
            result.setError(1);
            result.setMessage("图片上传失败");
        }
        return result;
    }
}
