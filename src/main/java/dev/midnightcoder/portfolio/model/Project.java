package dev.midnightcoder.portfolio.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Glabay | The Midnight Coder
 * @project Portfolio
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-06-20
 */
@Entity
@Getter @Setter
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "repository_url")
    private String repositoryUrl;

    @Column(name = "synopsis", length = 1000)
    private String synopsis;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
            this.updatedAt = createdAt;
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
