package com.example.board.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PostResponse {
    private final Long id;
    private final String title;
}
