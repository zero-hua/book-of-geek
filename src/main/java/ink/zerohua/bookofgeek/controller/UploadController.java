package ink.zerohua.bookofgeek.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;



/**
 * @program: book-of-geek
 * @describletion: 文件上传控制类
 * @author: zerohua
 * @create: 2020-06-23 12:45
 **/
@Slf4j
@Controller
@RequestMapping("/upload")
public class UploadController {

    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String UPLOAD_PATH_PREFIX = "/upload/";

    @ResponseBody
    @PostMapping("/image")
    public JSONObject uploadImage(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "editormd-image-file", required = false) MultipartFile upload){

        JSONObject jsonObject = new JSONObject();
        //项目根路径（绝对路径）
        String rootPath = Class.class.getClass().getResource("/public").getPath();
        //图片存放的路径(绝对路径)
        String realPath = rootPath+UPLOAD_PATH_PREFIX;

        log.info("图片上传");
        File file = new File(realPath);
        if (!file.exists()) {
            log.info("文件夹"+realPath+"不存在,新建......");
            //如果文件夹不存在，则创建文件夹
            file.mkdir();
        }
        //上传文件名
        String fileName = upload.getOriginalFilename();
        //生成唯一ID，防止文件因为同名被覆盖
        String uuid = String.valueOf(System.currentTimeMillis());
        fileName = uuid + fileName;
        //写进磁盘
        try {
            upload.transferTo(new File(realPath, fileName));
            jsonObject.put("success", 1);
            jsonObject.put("message", "上传成功");
            jsonObject.put("url", UPLOAD_PATH_PREFIX+fileName);
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put("success", 0);
            jsonObject.put("message", "上传失败");
        }
        return jsonObject;
    }
}
