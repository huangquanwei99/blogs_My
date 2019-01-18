package com.aidi.boke.controller;


import com.aidi.boke.domain.User;
import com.aidi.boke.domain.entity.Result;
import com.aidi.boke.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户controller
 */
@RestController
@RequestMapping("/login")
@Api(value = "用户接口", description = "用户接口管理,提供增加,修改,查询功能")
public class UserController {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    /**
     * 用户注册校验用户名是否被占用
     * 1 可用通过HttpServiceRequest 来获取前台传来的参数 getParameter
     * 2 通过属性名称来获取参数值
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ApiOperation("查询用户名是否存在接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名唯一", dataType = "string"),
    })
    public Result findMyName(String username) {
        System.out.println(username);
        // 通过用户名进行查询
        User user = userService.selectUserByName(username);
        // 判断用户名是否唯一,可用.
        if (user == null) {
            return new Result(true, "用户名可用");
        } else {
            return new Result(false, "用户名已被注册");
        }
    }

    /**
     * 注册保存操作
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ApiOperation("用户注册保存接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "用户信息", value = "用户名和密码")
    })
    public Result saveUser(User user) {
        try {
            Integer integer = userService.saveUser(user);
            log.info("用户:"+user.getUsername()+"注册成功");
            return new Result(true, "注册成功");
        } catch (Exception e) {
            e.printStackTrace();
            log.info("用户:"+user.getUsername()+"注册失败");
            return new Result(false, "用户注册失败");
        }
    }


    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "用户信息", value = "用户名和密码")
    })
    public Result loginUser(User user) {
        try {
            User userOne = userService.loginUser(user);
            if (!StringUtils.isEmpty(userOne)) {
                log.info("用户:"+user.getUsername()+"登录成功");
                return new Result(true, "登录成功");
            }
            log.info("用户:"+user.getUsername()+"登录失败");
            return new Result(false, "用户名或密码错误");
        } catch (Exception e) {
            log.info("用户:"+user.getUsername()+"登录出现异常");
            e.printStackTrace();
            return new Result(false, "用户名或密码错误");
        }
    }


    /**
     * @param object
     * @return
     * @throws Exception 判断一个对象中的属性是否为空值
     *                   返回结果
     *                   无值 返回 true
     *                   有值 返回 false
     */
    private boolean isAllFieldNull(Object object) throws Exception {
        Class stuCla = (Class) object.getClass(); // 得到类的对象
        Field[] declaredFields = stuCla.getDeclaredFields(); //得到类中属性
        boolean flag = true;
        for (Field f : declaredFields) { //遍历属性
            f.setAccessible(true); //设置属性是可以访问的,私有属性也可以
            Object val = f.get(object); //得到属性中的值
            if (val != null) {
                flag = false;
                break;
            }
        }
        return flag;

    }

    /**
     * Pattern p1 = Pattern.compile("^[\u2E80-\uFE4F·]{2,15}+$");
     */
    @Test
    public void expressionTest() {
        Pattern p1 = Pattern.compile("^[A-Z0-9]{9,10}$");
        Matcher m1 = p1.matcher("1231231");
        boolean flag = m1.matches();
        System.out.println(flag);

    }

    @Test
    public void expressionTest2() {
        Pattern p1 = Pattern.compile("^[\u2E80-\uFE4F·]{2,15}+$");
        Matcher m1 = p1.matcher("hao.yin");
        boolean flag = m1.matches();
        System.out.println(flag);

    }

    @Test
    public void statistics() {
        try {
            String name = new String("è?????è??".getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(name);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void html() {
        String str = "<asdasd>";
        str=str.replaceAll("<[^<>]+>","");
        System.out.println(str);

    }


}
