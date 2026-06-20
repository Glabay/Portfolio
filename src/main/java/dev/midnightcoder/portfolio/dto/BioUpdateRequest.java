package dev.midnightcoder.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
public class BioUpdateRequest {

    @NotBlank(message = "Bio cannot be blank")
    @Size(max = 2048, message = "Bio cannot exceed 2,048 characters")
    private String bio;
}
