package com.reddit.backend.mapper;

import com.reddit.backend.dto.PostRequest;
import com.reddit.backend.dto.PostResponse;
import com.reddit.backend.model.Post;
import com.reddit.backend.model.Subreddit;
import com.reddit.backend.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "subreddit" , source = "subreddit")
    @Mapping(target = "user" , source = "user")
    @Mapping(target = "description", source = "postRequest.description")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "postName" , source = "postName")
    @Mapping(target = "description" , source = "description")
    @Mapping(target = "url" , source = "url")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDto(Post post);

}
