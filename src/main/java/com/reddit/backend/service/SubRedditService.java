package com.reddit.backend.service;

import com.reddit.backend.dto.SubredditDto;
import com.reddit.backend.exception.SubRedditNotFoundException;
import com.reddit.backend.mapper.SubredditMapper;
import com.reddit.backend.model.Subreddit;
import com.reddit.backend.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class SubRedditService {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final SubredditMapper subredditMapper;

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(subreddit.getId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SubRedditNotFoundException("Subreddit not found with id -" + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }


}
