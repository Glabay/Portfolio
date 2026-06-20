package dev.midnightcoder.portfolio.service;

import dev.midnightcoder.portfolio.dto.RegistrationRequest;
import dev.midnightcoder.portfolio.model.AdminProfile;
import dev.midnightcoder.portfolio.model.Profile;
import dev.midnightcoder.portfolio.model.User;
import dev.midnightcoder.portfolio.repository.AdminProfileRepository;
import dev.midnightcoder.portfolio.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
public class ProfileService {
    private final ProfileRepository profileRepository;

    public Profile createProfile(User user, RegistrationRequest request) {
        var currentCount = profileRepository.count();
        var profile = currentCount == 0 ? new AdminProfile() : new Profile();
            profile.setFirstName(request.getFirstName());
            profile.setLastName(request.getLastName());
            profile.setUser(user);
        return profileRepository.save(profile);
    }

    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Profile getProfileForId(UUID uuid) {
        return profileRepository.findById(uuid).orElseThrow(() ->
            new IllegalArgumentException("Profile not found"));
    }

    public Profile getAdminProfile() {
        return profileRepository.findFirstByOrderByCreatedAtAsc()
            .orElse(null);
    }

    public Profile updateBio(UUID profileId, String bio) {
        var profile = getProfileForId(profileId);
        profile.setBio(bio);
        return profileRepository.save(profile);
    }
}
