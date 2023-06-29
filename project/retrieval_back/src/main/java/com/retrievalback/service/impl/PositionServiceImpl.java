package com.retrievalback.service.impl;

import com.retrievalback.entity.Fileposition;
import com.retrievalback.mapper.PositionMapper;
import com.retrievalback.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService {
    @Autowired
    private PositionMapper positionMapper;
    @Override
    public Integer addPosition(Fileposition fileposition){
        return positionMapper.insert(fileposition);
    }

}
