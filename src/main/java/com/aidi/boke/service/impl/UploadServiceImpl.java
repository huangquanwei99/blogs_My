package com.aidi.boke.service.impl;

import com.aidi.boke.dao.ImgBDMapper;
import com.aidi.boke.domain.ImgBD;
import com.aidi.boke.service.UploadService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huangquanwei on 2019/1/13 14:23
 * 查询用户文件列表
 */
@Service
public class UploadServiceImpl implements UploadService {

    @Autowired
      ImgBDMapper imgBDMapper;

    /**
     * 查询用户下所有文件
     * @param username
     * @return
     */
    @Override
    public List<ImgBD> findByName(String username) {
        if (!StringUtils.isBlank(username)){
            List<ImgBD> list=  imgBDMapper.findAll(username);
            return list;
        }
        return null;
    }

    /**
     *
     * @param username 用户名
     * @param originalFilename 原文件名
     * @param extName 文件扩展名称
     * @param url 文件具体路径
     * @return
     * 保存用户上传文件详细信息
     */
    @Override
    public boolean saveOne(String username, String originalFilename, String extName,String url) {
        try {
            ImgBD imgBD = new ImgBD();
            imgBD.setImgAddress(url);
            imgBD.setUsername(username);
            imgBD.setImgName(originalFilename);
            imgBD.setImgClass(1);//默认为1类型
            imgBD.setSort(1);  // 默认为1排序
            imgBD.setState(1); //默认1 有效
            imgBDMapper.saveOne(imgBD);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
