package com.sobow.reader.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class PostDto {

    private Long userId;
    private Long id;
    private String title;
    private String body;
}
