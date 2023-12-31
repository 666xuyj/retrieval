package com.retrievalback.controller;

import com.retrievalback.Result.Result;
import com.retrievalback.entity.Backfile;
import com.retrievalback.entity.CompareKey;
import com.retrievalback.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @Author:
 * @Data:2023/06/29
 * @Description:判断是否有匹配字符串
 */
@RestController
@CrossOrigin
public class CompareController {
    @Autowired
    PositionMapper positionMapper;
    @GetMapping("/api/cmp")
    public Result getCompare(@RequestParam("param") String  keyword) {
        try {
//            System.out.println(keyword);
            CompareKey compareKey = new CompareKey(positionMapper);
            ArrayList<Backfile> bckfile = new ArrayList<Backfile>();
            bckfile = compareKey.circleDb(keyword);
            if (bckfile.size() > 0) {
                return new Result(200, "success", bckfile);
            } else {
                return new Result(201, "fail", "no");
            }
        } catch (Exception e) {
            // 异常处理代码
            e.printStackTrace();
            return new Result(500, "error", "服务器内部错误");
        }
    }

}
