package com.aidi.boke.skipController;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by huangquanwei on 2018/12/29 15:26
 * 跳转到主页面
 */
@RequestMapping
public class IndexController {


    @RequestMapping("/")
    public String index(){
        System.out.println("skip 跳转");
        return "index";
    }
}
