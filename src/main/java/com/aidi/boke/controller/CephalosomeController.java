package com.aidi.boke.controller;

import com.aidi.boke.domain.Cephalosome;
import com.aidi.boke.domain.entity.Result;
import com.aidi.boke.service.CephalosomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by huangquanwei on 2019/1/2 16:15
 * pc端导航条主体controller
 */
@RestController
@RequestMapping("/pcTop")
@Api(value = "pc端页面入口", description = "pc端页面查询接口")
public class CephalosomeController {

    @Autowired
    private CephalosomeService cephalosomeService;

    @Autowired
    private RedisTemplate redisTemplate;

    private static Logger log = LoggerFactory.getLogger(CephalosomeController.class);

    @RequestMapping(value = "/head", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation("pc导航条接口")
    //@ApiImplicitParams() 无参数传递
    public Result headTop() {
        try {
            List<Cephalosome> list = (List<Cephalosome>) redisTemplate.boundHashOps("top").get("head");
            if (list == null) {
                list = cephalosomeService.findAll();
                redisTemplate.boundHashOps("top").put("head", list);
                log.info("topHead查询数据库");
            }else{
                log.info("topHead查询redis缓存");
            }
            return new Result(true, "查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "查询失败");
        }
    }
}
