package com.retrievalback.entity;

import org.apache.commons.compress.archivers.dump.InvalidFormatException;
import org.apache.ibatis.jdbc.Null;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.tika.Tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
/**
 * @Author:
 * @Data:2023/06/27
 * @Description:解析word和pdf
 */
public class ParseFile {
    /**
     * 解析word和pdf，由于word有两个版本所以多了一个判断
     * @param filePath
     * @return
     * @throws Exception
     */
    public String FilePars(String filePath) throws Exception {
//        System.out.println(filePath);
        String text = null;

        // 检查文件路径是否为空
        if (filePath.isEmpty()) {
            return "";
        }

        if (filePath.toLowerCase().endsWith("doc")) {
            InputStream file = new FileInputStream(new File(filePath));
            WordExtractor wordExtractor = new WordExtractor(file);
            text = wordExtractor.getText();
            file.close();
            wordExtractor.close();
        } else if (filePath.toLowerCase().endsWith("docx")) {
            OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);
            POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
            text = extractor.getText();
            opcPackage.close();
            extractor.close();
        } else if (filePath.toLowerCase().endsWith("pdf")) {
            PDDocument pdDocument;
            InputStream file = new FileInputStream(new File(filePath));
            pdDocument = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            text = stripper.getText(pdDocument);
            file.close();
            pdDocument.close();
        }


        return text;
    }



}
