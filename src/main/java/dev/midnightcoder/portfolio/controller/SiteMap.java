package dev.midnightcoder.portfolio.controller;

import dev.midnightcoder.portfolio.dto.RegistrationRequest;
import dev.midnightcoder.portfolio.service.ProfileService;
import dev.midnightcoder.portfolio.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Glabay | Glabay-Studios
 * @project Portfolio
 * @social Discord: Glabay
 * @since 2026-06-02
 */
@Controller
@RequiredArgsConstructor
public class SiteMap {
    private final ProjectService projectService;
    private final ProfileService profileService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("profile", profileService.getAdminProfile());
        model.addAttribute("projects", projectService.getAllProjects());
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "register";
    }

    @GetMapping("/profile")
    public String getProjectPage(Model model) {
        model.addAttribute("profile", profileService.getAdminProfile());
        model.addAttribute("projects", projectService.getAllProjects());
        return "index";
    }
}
