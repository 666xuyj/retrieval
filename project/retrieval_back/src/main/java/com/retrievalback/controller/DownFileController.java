package com.retrievalback.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;


@RestController
@CrossOrigin
public class DownFileController {
    @RequestMapping("/api/download")
    public String fileDownLoad(HttpServletResponse response, @RequestParam("fileName") String fileName){

        System.out.println(fileName);
        File file = new File(fileName);
        if(!file.exists()){
            return "下载文件不存在";
        }
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));) {
            byte[] buff = new byte[1024];
            OutputStream os  = response.getOutputStream();
            int i = 0;
            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
                os.flush();
            }
        } catch (IOException e) {
            return "下载失败";
        }
        return "下载成功";
    }
}
