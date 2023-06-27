package com.retrievalback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.retrievalback.mapper")
public class RetrievalbackApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetrievalbackApplication.class, args);
	}

}
