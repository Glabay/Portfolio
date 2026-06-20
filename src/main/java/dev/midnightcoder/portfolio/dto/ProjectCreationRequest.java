package dev.midnightcoder.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Glabay | The Midnight Coder
 * @project Portfolio
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-06-20
 */
@Getter @Setter
public class ProjectCreationRequest {
    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Repository URL is required")
    private String repositoryUrl;

    @NotBlank(message = "Synopsis is required")
    private String synopsis;
}
