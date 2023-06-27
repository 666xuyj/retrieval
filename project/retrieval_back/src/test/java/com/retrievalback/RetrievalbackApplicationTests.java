package com.retrievalback;

import com.retrievalback.entity.CompareKey;
import com.retrievalback.entity.FileTravel;
import com.retrievalback.entity.Fileposition;
import com.retrievalback.mapper.PositionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Iterator;
import java.util.List;

@SpringBootTest
class RetrievalbackApplicationTests {
	@Autowired
	PositionMapper positionMapper;
	@Test
	void contextLoads() throws Exception {
//		FileTravel fileTravel =new FileTravel(positionMapper);
//		fileTravel.traverse("D:\\workplace\\file");
		CompareKey compareKey =new CompareKey(positionMapper);
		compareKey.circleDb("arch=\"amd64\"");
//		String test = compareKey.comparing("D:\\workplace\\file\\1.docx");
//		System.out.println(test);
	}
//	@Test
//	void testInsert(){
//		FileTravel fileTravel = new FileTravel(positionMapper);
//		fileTravel.traverse("D:\\workplace\\file");
//	}

}
