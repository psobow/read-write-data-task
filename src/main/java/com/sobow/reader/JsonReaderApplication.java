package com.sobow.reader;

import com.sobow.reader.service.JSONService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class JsonReaderApplication implements CommandLineRunner {
	
	private final JSONService jsonService;
	
	public static void main(String[] args) {
		SpringApplication.run(JsonReaderApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		jsonService.savePostsToFiles();
		System.exit(0);
	}
}
