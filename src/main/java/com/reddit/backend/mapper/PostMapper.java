package com.reddit.backend.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.reddit.backend.dto.PostRequest;
import com.reddit.backend.dto.PostResponse;
import com.reddit.backend.model.*;
import com.reddit.backend.repository.CommentRepository;
import com.reddit.backend.repository.VoteRepository;
import com.reddit.backend.service.AuthService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.reddit.backend.model.VoteType.DOWNVOTE;
import static com.reddit.backend.model.VoteType.UPVOTE;

@Mapper(componentModel = "spring")
public abstract class PostMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private AuthService authService;


    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    @Mapping(target = "subreddit", source = "subreddit")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "user", source = "user")
    public abstract Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "duration", expression = "java(getDuration(post))")
    @Mapping(target = "upVote", expression = "java(isPostUpVoted(post))")
    @Mapping(target = "downVote", expression = "java(isPostDownVoted(post))")
    public abstract PostResponse mapToDto(Post post);

    Integer commentCount(Post post) {
        return commentRepository.findByPost(post).size();
    }

    String getDuration(Post post) {
        return TimeAgo.using(post.getCreatedDate().toEpochMilli());
    }

    boolean isPostUpVoted(Post post) {
        return checktypeVoted(post, UPVOTE);
    }

    boolean isPostDownVoted(Post post) {
        return checktypeVoted(post, DOWNVOTE);
    }

    private boolean checktypeVoted(Post post, VoteType voteType) {
        if (authService.isloggedIn()) {
            Optional<Vote> voteForPostUser = voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
            return voteForPostUser.filter(vote -> vote.getVoteType().equals(voteType))
                    .isPresent();
        }

        return false;
    }

}
