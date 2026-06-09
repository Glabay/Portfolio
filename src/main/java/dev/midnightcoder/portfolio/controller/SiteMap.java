package dev.midnightcoder.portfolio.controller;

import dev.midnightcoder.portfolio.dto.RegistrationRequest;
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
public class SiteMap {

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String getRegistrationPage(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "register";
    }
}
