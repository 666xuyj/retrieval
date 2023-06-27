package com.retrievalback.entity;
import com.retrievalback.mapper.PositionMapper;
import com.retrievalback.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
/**
 * @Author:DuanQianQain
 * @Data:2023/06/27
 * @Description:遍历文件夹取出pdf和word
 */
public class FileTravel {
    private final PositionMapper positionMapper;

    /**
     * 需要一个Autowired不然在插入时会有空指针所以草率设置了一个私有变量来存放
     * @param positionMapper
     */
    public FileTravel(PositionMapper positionMapper) {
        this.positionMapper = positionMapper;
    }

    /**
     * 递归遍历目录和子目录
     * @param path 读哪个盘的文件
     */
    public void traverse(String path) {
        File file = new File(path);

        if(file.isDirectory()){
            File[] files = file.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".docx") || name.endsWith(".pdf");
                }
            });
            if(files!=null){
                for(File f:files){
                    traverse(f.getAbsolutePath());
                }
            }
            // 遍历子目录
            File[] dirs = file.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.isDirectory();
                }
            });
            if (dirs != null) {
                for (File dir : dirs) {
                    traverse(dir.getAbsolutePath());  // 递归调用 traverse 方法
                }
            }
        }else{
            Fileposition fileposition = new Fileposition(file.getName(), file.getAbsolutePath());
            this.positionMapper.insert(fileposition);
        }
    }

}
