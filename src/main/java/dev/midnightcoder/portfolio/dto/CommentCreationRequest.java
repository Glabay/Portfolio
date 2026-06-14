package dev.midnightcoder.portfolio.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author Glabay | The Midnight Coder
 * @project Portfolio
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-06-14
 */
@Getter @Setter
public class CommentCreationRequest {
    private UUID userId;
    private UUID postId;
    private String content;
}
