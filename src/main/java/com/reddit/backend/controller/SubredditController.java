package com.reddit.backend.controller;

import com.reddit.backend.dto.SubredditDto;
import com.reddit.backend.service.SubRedditService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
public class SubredditController {

    private final SubRedditService subRedditService;

    @GetMapping
    public List<SubredditDto> getAllSubreddits() {
        return subRedditService.getAll();
    }
    @GetMapping("/{id}")
    public SubredditDto getSubreddit(@PathVariable Long id) {
        return subRedditService.getSubreddit(id);
    }

    @PostMapping
    public SubredditDto create(@RequestBody @Valid SubredditDto subredditDto) {
        return subRedditService.save(subredditDto);
    }

}
