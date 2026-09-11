package com.example.board.controller;

import com.example.board.dto.PostCreateRequest;
import com.example.board.dto.PostResponse;
import com.example.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponse> findById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @PostMapping("/posts")
    public ResponseEntity<PostResponse> create(
            @RequestBody PostCreateRequest request
    ) {
        PostResponse post = postService.create(request);
        return ResponseEntity.created(URI.create("/posts/" + post.getId())).body(post);
    }
}
