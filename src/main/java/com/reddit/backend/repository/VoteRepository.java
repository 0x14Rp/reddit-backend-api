package com.reddit.backend.repository;

import com.reddit.backend.model.Post;
import com.reddit.backend.model.User;
import com.reddit.backend.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote,Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
