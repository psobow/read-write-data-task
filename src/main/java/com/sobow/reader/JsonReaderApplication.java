package com.sobow.reader;

import com.google.gson.Gson;
import com.sobow.reader.client.JSONPlaceholderClient;
import com.sobow.reader.dto.PostDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class JsonReaderApplication implements CommandLineRunner {
	
	private final JSONPlaceholderClient client;
	private final Gson gson;
	
	public static void main(String[] args) {
		SpringApplication.run(JsonReaderApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		List<PostDto> response = client.getPosts();
		log.debug(response.get(0).toString());
		
		String jsonString = gson.toJson(response.get(0));
		System.out.println("\n" + jsonString);
	}
}
