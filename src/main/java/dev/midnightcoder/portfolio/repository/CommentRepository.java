package dev.midnightcoder.portfolio.repository;

import dev.midnightcoder.portfolio.model.Comment;
import dev.midnightcoder.portfolio.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Glabay | The Midnight Coder
 * @project Portfolio
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-06-14
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findCommentByPostId(UUID postId);
    List<Comment> findCommentByPostId(UUID postId, Pageable pageable);
}
