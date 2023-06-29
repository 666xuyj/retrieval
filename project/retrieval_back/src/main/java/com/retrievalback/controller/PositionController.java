package com.retrievalback.controller;

import com.retrievalback.Result.Result;
import com.retrievalback.entity.FileTravel;
import com.retrievalback.entity.Fileposition;
import com.retrievalback.mapper.PositionMapper;
import com.retrievalback.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;

/**
 * @Author:
 * @Data:2023/06/29
 * @Description:提供遍历路径接口
 */
@CrossOrigin
@RestController
public class PositionController {
    @Autowired
    PositionMapper positionMapper;
    @RequestMapping("/api/path")
    public Result addPosition(@RequestParam("param") String  path){
        FileTravel fileTravel = new FileTravel(positionMapper);
        fileTravel.traverse(path);
        if(fileTravel.getIsTraversed()){
            return new Result(200,"success","");
        }else{
            return new Result(201,"fail","");
        }

    }
    @RequestMapping("/api/select")
    public Result selectPath(){
        positionMapper.delectAll();
        ArrayList<String> filedir = new ArrayList<>();
        FileTravel fileTravel = new FileTravel(positionMapper);
        File file = new File("D:\\");
        filedir = fileTravel.traverse(file);
        return new Result(200,"success",filedir);
    }

}
