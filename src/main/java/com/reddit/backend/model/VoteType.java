package com.reddit.backend.model;

public enum VoteType {

    UPVOTE(1), DOWNVOTE(-1),
    ;

    VoteType(int direction) {
    }
}
