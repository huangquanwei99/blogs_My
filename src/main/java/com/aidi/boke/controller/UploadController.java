package com.aidi.boke.controller;


import ch.qos.logback.core.util.FileUtil;
import com.aidi.boke.domain.ImgBD;
import com.aidi.boke.domain.User;
import com.aidi.boke.domain.entity.Result;
import com.aidi.boke.service.UploadService;
import com.aidi.boke.service.UserService;
import com.aidi.boke.utils.FastDFSClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 上传图片到服务器
 */
@RestController
@Api(value = "文件上传接口", description = "用户文件上传保存")
public class UploadController {

    @Value("${FILE_SERVER_URL}")
    private String file_server_url;

    @Autowired
    private UploadService uploadService;
    @Autowired
    UserService userService;
    private static Logger log = LoggerFactory.getLogger(UploadController.class);

    /**
     * 文件上传
     * @param file
     * @return
     */

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ApiOperation("用户上传文件入口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file",value = "文件格式",dataType = "file"),
            @ApiImplicitParam(name="username",value = "用户名称",dataType = "String")
    })
    public Result upload(MultipartFile file ,String username){

        if (username == null || username ==""){ //判断用户名是否传入
            return  new Result(false, "用户名为空");
        }
        User user = userService.selectUserByName(username);
        if (user == null){ //判断用户是否存在
            return  new Result(false, "用户不存在,不能保存.请先注册用户!");
        }
        String originalFilename = null;//获取文件名
        try {
            originalFilename = file.getOriginalFilename();
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false, "保存失败");
        }
        //判断前段传的照片是否为空
        if (originalFilename !=null && originalFilename!=""){
            System.out.println(originalFilename);
            String extName=originalFilename.substring( originalFilename.lastIndexOf(".")+1);//得到扩展名
            System.out.println(extName);
            try {
                FastDFSClient client=new FastDFSClient();
                String fileId = client.uploadFile(file.getBytes(), extName);
                String url=file_server_url+fileId;//文件在服务器完整地址
                System.out.println(username);
                boolean bl = uploadService.saveOne(username,originalFilename,extName,url);
                if (bl){
                    log.info("用户>>>"+username+"<<<保存文件"+originalFilename+"成功");
                    return new Result(true, url);
                }
                return new Result(false, "保存失败");
            } catch (Exception e) {
                e.printStackTrace();
                log.info("出现异常保存失败");
                return new Result(false, "保存失败");
            }
        }
        return  new Result(false, "保存失败");
    }
    /**
     * 文件下载
     */
    @RequestMapping("/download")
    @ResponseBody
    public Map fileDownLoad(String group, String filename) {
        Map map = new HashMap();
        return map;
    }

    /**
     * 获取文件列表
     */
    @RequestMapping(value = "/findByName",method = {RequestMethod.GET,RequestMethod.POST})
    @ApiOperation("获取文件列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名",dataType = "String")
    })
    public Map findByName(String username) {
        Map map = new HashMap();
        List<ImgBD> list  = uploadService.findByName(username);
        map.put("code",200);
        map.put("msg","查询成功");
        map.put("list",list);
        return map;
    }


}
