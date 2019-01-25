package com.aidi.boke.dao;

import com.aidi.boke.domain.ImgBD;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangquanwei on 2019/1/13 14:08
 * 用户文件Dao
 */
@Repository
public interface ImgBDMapper {
    //头部查询所有接口
    List<ImgBD> findAll(ImgBD imgBD);
    //保存
    void saveOne(ImgBD imgBD);


}
