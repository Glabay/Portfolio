package dev.midnightcoder.portfolio.service;

import dev.midnightcoder.portfolio.model.User;
import dev.midnightcoder.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
public class UserService {
    private final UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsernameIgnoreCase(username)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found"));
    }

    public User getUserByUserId(UUID userId) {
        return userRepository.findById(userId)
            .orElseThrow(() ->
                new IllegalArgumentException("User not found with ID: " + userId));
    }
}
