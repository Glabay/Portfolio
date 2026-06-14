package dev.midnightcoder.portfolio.controller;

import dev.midnightcoder.portfolio.dto.PostCreationRequest;
import dev.midnightcoder.portfolio.model.AdminProfile;
import dev.midnightcoder.portfolio.service.PostService;
import dev.midnightcoder.portfolio.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @author Glabay | The Midnight Coder
 * @project Portfolio
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-06-14
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/blogs")
public class BlogController {
    private final UserService userService;
    private final PostService postService;

    @GetMapping
    public String getBlog(Authentication authentication, Model model) {
        if (authentication == null ||
            !authentication.isAuthenticated() ||
            (authentication instanceof AnonymousAuthenticationToken)
        ) return "redirect:/login";

        var username = authentication.getName();
        var user = userService.getUserByUsername(username);
        var profile = user.getProfile();
        var isOwner = profile instanceof AdminProfile;
        model.addAttribute("isOwner", isOwner);
        model.addAttribute("posts", postService.getAllPosts());
        return "blog";
    }

    @PostMapping("/post")
    public String createPost(@Valid @RequestBody PostCreationRequest request,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/error";
        }
        postService.createPost(request);
        return "redirect:/blogs";
    }

    @GetMapping("/post/{id}")
    public String getPost(@PathVariable String id, Authentication authentication, Model model) {
        if (authentication == null ||
            !authentication.isAuthenticated() ||
            (authentication instanceof AnonymousAuthenticationToken)
        ) return "redirect:/login";

        var username = authentication.getName();
        var user = userService.getUserByUsername(username);

        var uuid = UUID.fromString(id);
        var post = postService.getPostForId(uuid);
        model.addAttribute("post", post);
        model.addAttribute("userId", user.getId());
        return "post";
    }
}
