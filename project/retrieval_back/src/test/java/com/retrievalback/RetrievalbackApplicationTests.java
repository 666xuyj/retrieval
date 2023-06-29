package com.retrievalback;

import com.retrievalback.entity.CompareKey;
import com.retrievalback.entity.FileTravel;
import com.retrievalback.entity.Fileposition;
import com.retrievalback.entity.ParseFile;
import com.retrievalback.mapper.PositionMapper;
import org.apache.poi.openxml4j.exceptions.NotOfficeXmlFileException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//@SpringBootTest
class RetrievalbackApplicationTests {
	@Autowired
	PositionMapper positionMapper;
	@Test
	void contextLoads() throws Exception {
		ParseFile parseFile = new ParseFile();

		parseFile.FilePars("D:\\$RECYCLE.BIN\\S-1-5-21-1912530546-937909238-3611953852-1001\\$I1N4BEH.docx");


//		ArrayList<String> filedir = new ArrayList<>();
//		FileTravel fileTravel = new FileTravel(positionMapper);
//		File file = new File("D:\\");
//		filedir = fileTravel.traverse(file);
//		for (String item : filedir) {
//			System.out.println(item);
//		}


	}
//	@Test
//	void testInsert(){
//		FileTravel fileTravel = new FileTravel(positionMapper);
//		fileTravel.traverse("D:\\workplace\\file");
//	}

}
