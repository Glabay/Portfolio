package dev.midnightcoder.portfolio.service;

import dev.midnightcoder.portfolio.dto.ProjectCreationRequest;
import dev.midnightcoder.portfolio.model.Project;
import dev.midnightcoder.portfolio.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author Glabay | The Midnight Coder
 * @project Portfolio
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-06-20
 */
@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Transactional
    public void createProject(ProjectCreationRequest request) {
        var project = new Project();
        project.setName(request.getName());
        project.setRepositoryUrl(request.getRepositoryUrl());
        project.setSynopsis(request.getSynopsis());
        projectRepository.save(project);
    }

    public @Nullable Project getProjectForId(UUID uuid) {
        return projectRepository.findById(uuid)
            .orElseThrow(() -> new IllegalArgumentException("Project not found"));
    }

    public @Nullable List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public List<Project> getTop3Projects() {
        return projectRepository.findTop3ByOrderByCreatedAtDesc();
    }
}
