package com.retrievalback.entity;

import com.retrievalback.mapper.PositionMapper;
import org.apache.poi.openxml4j.exceptions.InvalidOperationException;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author:
 * @Data:2023/06/27
 * @Description:匹配关键字
 *
 */
public class CompareKey {
    private PositionMapper positionMapper;
    public CompareKey(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }
    public ArrayList<Backfile> circleDb(String keyword) {
        List<Fileposition> list = this.positionMapper.selectList(null);
        Iterator<Fileposition> iterator = list.iterator();
        ArrayList<Backfile> bckfile = new ArrayList<Backfile>();

        try {
            while (iterator.hasNext()) {
                if (keyword == null) {
                    break;
                }

                Backfile bck = new Backfile();
                Fileposition str = iterator.next();
                String path = str.getPositioncol();

                String text;
                try {
                    text = comparing(path);
                } catch (NotOfficeXmlFileException e) {
                    System.err.println("Error occurred while comparing the file: " + path);
                    e.printStackTrace();
                    continue; // 继续下一次迭代
                }catch (IOException e){
                    e.printStackTrace();
                    continue;
                }catch (InvalidOperationException e){
                    e.printStackTrace();
                    continue;
                }

                if (text == null) {
                    return bckfile;
                }

                String[] lines = text.split("\\r?\\n");
                int lineNumber = 0;
                int titleflag = 0;

                for (String line : lines) {
                    lineNumber++;

                    if (line.contains(keyword)) {
                        if (titleflag == 0) {
                            bck.setPath(path);
                            titleflag = 1;
                        }

                        String tmp = "行号" + lineNumber + "\t" + line;
                        bck.setInfo(tmp);
                    }
                }

                if (bck.getPath() != null) {
                    bckfile.add(bck);
                }
            }
        } catch (Exception e) {
            System.err.println("Error occurred in the 'circleDb' method:");
            e.printStackTrace();
        }

        return bckfile;
    }
    public String comparing(String path) throws  Exception{
        ParseFile parseFile = new ParseFile();
        String text = parseFile.FilePars(path);
        return  text;
    }


}
