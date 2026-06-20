package dev.midnightcoder.portfolio.repository;

import dev.midnightcoder.portfolio.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author Glabay | The Midnight Coder
 * @project Portfolio
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-06-20
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, UUID> {
    List<Project> findTop3ByOrderByCreatedAtDesc();
}
