package com.reddit.backend.repository;

import com.reddit.backend.model.Comment;
import com.reddit.backend.model.Post;
import com.reddit.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}
