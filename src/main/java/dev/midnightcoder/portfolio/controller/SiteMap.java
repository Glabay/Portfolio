package dev.midnightcoder.portfolio.controller;

import org.springframework.stereotype.Controller;
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
}
