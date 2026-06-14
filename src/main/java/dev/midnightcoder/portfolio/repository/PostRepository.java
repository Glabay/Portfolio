package dev.midnightcoder.portfolio.repository;

import dev.midnightcoder.portfolio.model.Post;
import dev.midnightcoder.portfolio.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author Glabay | The Midnight Coder
 * @project Portfolio
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-06-14
 */
@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
}
