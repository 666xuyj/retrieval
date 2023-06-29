package com.retrievalback.controller;

import com.retrievalback.entity.ParseFile;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @Author:
 * @Data:2023/06/29
 * @Description:提供下载接口
 */

@RestController
@CrossOrigin
public class DownFileController {
    @RequestMapping("/api/download")
    public String fileDownLoad(HttpServletResponse response, @RequestParam("fileName") String fileName,@RequestParam("key") String keyword) throws Exception {
        String saveFilename = "file.txt";
        File f = new File(fileName);
        File file = new File(saveFilename);

        saveFile(fileName,saveFilename,keyword);

        if(!f.exists()){
            return "下载文件不存在";
        }
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=xxx.txt" );
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            OutputStream os = response.getOutputStream();
            byte[] buff = new byte[1024];
            int bytesRead;
            while ((bytesRead = bis.read(buff)) != -1) {
                os.write(buff, 0, bytesRead);
                os.flush();
            }
        } catch (IOException e) {
            return "下载失败";
        }
        file.delete();
        return "下载成功";
    }

    /**
     * 将关键行暂时存放在一个文件中
     * @param path
     * @param filename
     * @param keyword
     * @throws Exception
     */
    public void saveFile(String path,String filename,String keyword)throws Exception{
        ParseFile parseFile = new ParseFile();
        String text = parseFile.FilePars(path);
        File txtFile = new File(filename);
        BufferedWriter writer = new BufferedWriter(new FileWriter(txtFile));

        String[] lines = text.split("\\r?\\n");
        int lineNumber = 0;
        String s = "";
        for (String line : lines) {
            lineNumber++;
            if (line.contains(keyword)) {
                s += "行号"+lineNumber+"\t"+line+"\r";

            }
        }
        writer.write(s);
        writer.close();

    }
}
