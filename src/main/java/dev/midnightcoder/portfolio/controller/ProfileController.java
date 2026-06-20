package dev.midnightcoder.portfolio.controller;

import dev.midnightcoder.portfolio.dto.BioUpdateRequest;
import dev.midnightcoder.portfolio.model.Profile;
import dev.midnightcoder.portfolio.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author Glabay | The Midnight Coder
 * @project Portfolio
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-06-14
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<Profile>> getProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfileById(@PathVariable String id) {
        var uuid = UUID.fromString(id);
        return ResponseEntity.ok(profileService.getProfileForId(uuid));
    }

    @PutMapping("/{id}/bio")
    public ResponseEntity<Profile> updateBio(@PathVariable String id,
                                             @Valid @RequestBody BioUpdateRequest request,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // TODO: Handle validation errors
            //      - Challenge: Implement error handling for validation errors
            return ResponseEntity.badRequest().build();
        }

        var uuid = UUID.fromString(id);
        return ResponseEntity.ok(profileService.updateBio(uuid, request.getBio()));
    }
}
