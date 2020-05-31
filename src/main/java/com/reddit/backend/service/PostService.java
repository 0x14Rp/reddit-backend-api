package com.reddit.backend.service;

import com.reddit.backend.dto.PostRequest;
import com.reddit.backend.dto.PostResponse;
import com.reddit.backend.exception.PostNotFoundException;
import com.reddit.backend.exception.SubRedditNotFoundException;
import com.reddit.backend.mapper.PostMapper;
import com.reddit.backend.model.Post;
import com.reddit.backend.model.Subreddit;
import com.reddit.backend.model.User;
import com.reddit.backend.repository.PostRepository;
import com.reddit.backend.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;

    public Post save(PostRequest postRequest) {

        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubRedditNotFoundException(postRequest.getSubredditName()));
        User currentUser = authService.getCurrentUser();

        return postMapper.map(postRequest, subreddit, currentUser);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }



}
