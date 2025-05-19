package com.personal.BlogApp.comments;


import org.springframework.stereotype.Service;

@Service
public class CommentsService {

    private CommentRepository commentRepository;

    public CommentsService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
