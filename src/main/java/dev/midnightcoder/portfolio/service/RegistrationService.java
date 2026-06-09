package dev.midnightcoder.portfolio.service;

import dev.midnightcoder.portfolio.model.User;
import dev.midnightcoder.portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Glabay | Glabay-Studios
 * @project Portfolio
 * @social Discord: Glabay
 * @since 2026-06-08
 */
@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;

    public boolean userExists(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isPresent();
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
