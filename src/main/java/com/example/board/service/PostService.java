package com.example.board.service;

import com.example.board.dto.PostCreateRequest;
import com.example.board.dto.PostResponse;
import com.example.board.entity.Post;
import com.example.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostResponse findById(
            Long id
    ) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new PostResponse(post.getId(), post.getTitle());
    }

    @Transactional
    public PostResponse create(
            PostCreateRequest request
    ) {
        if (!StringUtils.hasText(request.getTitle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Post post = postRepository.save(new Post(request.getTitle()));
        return new PostResponse(post.getId(), post.getTitle());
    }
}
