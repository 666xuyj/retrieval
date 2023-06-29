package com.retrievalback.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.retrievalback.entity.Fileposition;
import org.apache.ibatis.annotations.Update;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @Author:
 * @Data:2023/06/27
 * @Description:存放文件信息和位置的mapper
 */
public interface PositionMapper extends BaseMapper<Fileposition> {
    @Update("truncate table postion_data.position")
    void delectAll();
}
