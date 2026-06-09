package dev.midnightcoder.portfolio.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Glabay | Glabay-Studios
 * @project Portfolio
 * @social Discord: Glabay
 * @since 2026-06-08
 */
@Getter
@Setter
public class RegistrationRequest {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
