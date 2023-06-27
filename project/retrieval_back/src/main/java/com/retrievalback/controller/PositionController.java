package com.retrievalback.controller;

import com.retrievalback.Result.Result;
import com.retrievalback.entity.Fileposition;
import com.retrievalback.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PositionController {
    @Autowired
    PositionService positionService;
    @PostMapping("/api/position")
    public Result addPosition(@RequestBody Fileposition fileposition){
        return new Result(200,"",positionService.addPosition(fileposition));
    }
}
