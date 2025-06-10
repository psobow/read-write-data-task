package com.sobow.reader.service;

import com.google.gson.Gson;
import com.sobow.reader.client.JSONPlaceholderClient;
import com.sobow.reader.dto.PostDto;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class JSONService {
    
    private final JSONPlaceholderClient client;
    private final Gson gson;
    
    private final String postsPathFromRepoRoot = "src/main/resources/posts/";
    
    public void savePostsToFiles() {
        initDirectory();
        
        List<PostDto> posts = client.getPosts();
        
        log.info("Creating files under: " + "\"" + postsPathFromRepoRoot + "\"");
        for (PostDto post : posts) {
            String fileName = postsPathFromRepoRoot + post.getId() + ".json";
            File tempFile = new File(fileName);
            try {
                tempFile.createNewFile();
                
                FileWriter writer = new FileWriter(tempFile);
                writer.write(gson.toJson(post));
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    private void initDirectory() {
        File directory = new File(postsPathFromRepoRoot);
        if (!directory.exists()) {
            directory.mkdir();
            log.debug("Creating dir: " + postsPathFromRepoRoot);
        }
        
        Arrays.stream(directory.listFiles())
              .forEach(File::delete);
        log.debug("Deleting all files in: " + postsPathFromRepoRoot);
    }
}
