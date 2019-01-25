package com.aidi.boke.service;

import com.aidi.boke.domain.ImgBD;

import java.util.List;

/**
 * Created by huangquanwei on 2019/1/13 14:22
 */
public interface UploadService {

    // 查询用户所有列表
    List<ImgBD> findByName(ImgBD imgBD);
    //保存
    boolean saveOne(String username, String originalFilename, String extName ,String url);
}
