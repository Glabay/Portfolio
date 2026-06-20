package dev.midnightcoder.portfolio.controller;

import dev.midnightcoder.portfolio.dto.ProjectCreationRequest;
import dev.midnightcoder.portfolio.model.AdminProfile;
import dev.midnightcoder.portfolio.model.User;
import dev.midnightcoder.portfolio.service.ProjectService;
import dev.midnightcoder.portfolio.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Glabay | The Midnight Coder
 * @project Portfolio
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-06-20
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/projects")
public class ProjectController {
    private static final Logger log = LoggerFactory.getLogger(ProjectController.class);
    private final UserService userService;
    private final ProjectService projectService;

    @GetMapping
    public String getProjects(Authentication authentication, Model model) {
        var isOwner = false;
        var user = getUsername(authentication);
        if (user != null) {
            isOwner = user.getProfile() instanceof AdminProfile;
        }
        model.addAttribute("isOwner", isOwner);
        model.addAttribute("projects", projectService.getAllProjects());
        return "projects";
    }

    @PostMapping
    public String createProject(@Valid @RequestBody ProjectCreationRequest request,
                                BindingResult bindingResult,
                                Authentication authentication,
                                Model model) {
        if (authentication == null ||
            !authentication.isAuthenticated() ||
            (authentication instanceof AnonymousAuthenticationToken)
        ) return "redirect:/login";

        var user = getUsername(authentication);
        if (user == null || !(user.getProfile() instanceof AdminProfile)) {
            return "redirect:/error";
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/error";
        }
        projectService.createProject(request);
        return "redirect:/projects";
    }

    private User getUsername(Authentication authentication) {
        try {
            var username = "";
            if (authentication != null &&
                authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)
            ) username = authentication.getName();
            return userService.getUserByUsername(username);
        }
        catch (UsernameNotFoundException _) {
            log.error("User not found");
        }
        return null;
    }
}
