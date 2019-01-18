package com.aidi.boke.service.impl;

import com.aidi.boke.dao.CephalosomeMapper;
import com.aidi.boke.domain.Cephalosome;
import com.aidi.boke.service.CephalosomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by huangquanwei on 2019/1/2 16:20
 * 导航条主体实现类
 */
@Service
public class CephalpsemeServiceImpl implements CephalosomeService {

    @Autowired
    private  CephalosomeMapper cephalosomeMapper;


    @Override
    public List<Cephalosome> findAll() {
        //进行list排序
        List<Cephalosome> all = cephalosomeMapper.findAll();
        Collections.sort(all);
        return all;
    }
}
