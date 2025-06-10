package com.sobow.reader.client;

import com.sobow.reader.dto.PostDto;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
@Slf4j
public class JSONPlaceholderClient {
    
    private final RestTemplate restTemplate;
    
    private final String scheme = "https";
    private final String domain = "jsonplaceholder.typicode.com";
    
    private final String posts = "/posts";
    
    public List<PostDto> getPosts() {

        URI uri = UriComponentsBuilder.newInstance()
                                      .scheme(scheme)
                                      .host(domain)
                                      .path(posts)
                                      .build()
                                      .encode()
                                      .toUri();
        log.debug("Calling URI: {}", uri);
        
        try {
            PostDto[] response = restTemplate.getForObject(uri, PostDto[].class);
            return Arrays.asList(Optional.ofNullable(response)
                                         .orElse(new PostDto[0]));
        } catch (RestClientException e) {
            log.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }
}
