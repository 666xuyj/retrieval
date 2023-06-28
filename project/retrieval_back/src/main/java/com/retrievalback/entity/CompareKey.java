package com.retrievalback.entity;

import com.retrievalback.mapper.PositionMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author:chenjinye
 * @Data:2023/06/27
 * @Description:匹配关键字
 *
 */
public class CompareKey {
    private PositionMapper positionMapper;
    public CompareKey(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }
    public  ArrayList<Backfile> circleDb(String keyword) throws Exception{
        List<Fileposition> list = this.positionMapper.selectList(null);
        Iterator<Fileposition> iterator = list.iterator();
        ArrayList<Backfile> bckfile = new ArrayList<Backfile>();
        while (iterator.hasNext()) {
            if(keyword == null){
                break;
            }
            Backfile bck = new Backfile();
            Fileposition str = iterator.next();

            String path = str.getPositioncol();
            String text = comparing(path);

            String[] lines = text.split("\\r?\\n");
            int lineNumber = 0;
            int titleflag = 0;
            for (String line : lines) {
                lineNumber++;
                if (line.contains(keyword)) {
                    if(titleflag ==0){
                        bck.setPath(path);
                        titleflag = 1;
                    }
                    String tmp = "行号"+lineNumber+"\t"+line;
                    bck.setInfo(tmp);

                }
            }
            if(bck.getPath() != null){
                bckfile.add(bck);
            }

        }
        return bckfile;
    }
    public String comparing(String path) throws  Exception{
        ParseFile parseFile = new ParseFile();
        String text = parseFile.FilePars(path);
        return  text;
    }


}
