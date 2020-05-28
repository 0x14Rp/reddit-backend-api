package com.reddit.backend.repository;


import com.reddit.backend.model.Post;
import com.reddit.backend.model.Subreddit;
import com.reddit.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository  extends JpaRepository<Post,Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
