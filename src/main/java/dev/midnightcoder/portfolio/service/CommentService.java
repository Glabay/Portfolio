package dev.midnightcoder.portfolio.service;

import dev.midnightcoder.portfolio.dto.CommentCreationRequest;
import dev.midnightcoder.portfolio.model.Comment;
import dev.midnightcoder.portfolio.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Glabay | The Midnight Coder
 * @project Portfolio
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-06-14
 */
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    public Comment createComment(CommentCreationRequest request) {
        var author = userService.getUserByUserId(request.getUserId());
        var post = postService.getPostForId(request.getPostId());
        var comment = new Comment();
            comment.setProfile(author.getProfile());
            comment.setPost(post);
            comment.setContent(request.getContent());
        return commentRepository.save(comment);
    }

    public List<Comment> fetchCommentsByPostId(UUID postId) {
        return commentRepository.findCommentByPostId(postId);
    }

    public List<Comment> fetchCommentsByPostId(UUID postId, Pageable pageable) {
        return commentRepository.findCommentByPostId(postId, pageable);
    }
}
