package com.aidi.boke.dao;

import com.aidi.boke.domain.Cephalosome;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangquanwei on 2019/1/2 16:28
 */
@Repository
public interface CephalosomeMapper {
    
    //头部查询所有接口
    List<Cephalosome> findAll();
    
}
