package com.retrievalback.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * @Author:
 * @Data:2023/06/27
 * @Description:创建position实体类，文件名和文件位置
 */
@TableName(value = "position")
public class Fileposition {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String filename;
    private String positioncol;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Fileposition(String filename, String positioncol) {
        this.filename = filename;
        this.positioncol = positioncol;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPositioncol() {
        return positioncol;
    }

    public void setPositioncol(String positioncol) {
        this.positioncol = positioncol;
    }
}
