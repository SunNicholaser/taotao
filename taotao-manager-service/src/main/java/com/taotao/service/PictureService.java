package com.taotao.service;

import com.taotao.common.pojo.PictureResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ASUS on 2017/10/31.
 */
public interface PictureService {
    PictureResult uploadPic(MultipartFile picFile);
}
