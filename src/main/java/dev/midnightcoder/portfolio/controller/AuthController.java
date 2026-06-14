package dev.midnightcoder.portfolio.controller;

import dev.midnightcoder.portfolio.dto.RegistrationRequest;
import dev.midnightcoder.portfolio.model.User;
import dev.midnightcoder.portfolio.service.ProfileService;
import dev.midnightcoder.portfolio.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

/**
 * @author Glabay | Glabay-Studios
 * @project Portfolio
 * @social Discord: Glabay
 * @since 2026-06-08
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    private final ProfileService profileService;
    private final RegistrationService registrationService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping(value = "/register", consumes = "application/x-www-form-urlencoded")
    public String registerUser(@ModelAttribute RegistrationRequest request) {
        if (request == null) {
            return "redirect:/register?noCreds";
        }
        if (registrationService.userExists(request.getUsername())) {
            return "redirect:/register?userExists";
        }
        if (!Objects.equals(request.getPassword(), request.getConfirmPassword())) {
            return "redirect:/register?passwordMismatch";
        }
        var user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(passwordEncoder.encode(request.getPassword()));

        var savedUser = registrationService.save(user);
        var profile = profileService.createProfile(savedUser, request);
        user.setProfile(profile);
        registrationService.save(user);
        return "redirect:/login?registered";
    }
}
