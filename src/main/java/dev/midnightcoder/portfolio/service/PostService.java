package dev.midnightcoder.portfolio.service;

import dev.midnightcoder.portfolio.dto.PostCreationRequest;
import dev.midnightcoder.portfolio.model.Post;
import dev.midnightcoder.portfolio.repository.PostRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public void createPost(PostCreationRequest request) {
        var post = new Post();
            post.setTitle(request.getTitle());
            post.setContent(request.getContent());
        postRepository.save(post);
    }

    public @Nullable Post getPostForId(UUID uuid) {
        return postRepository.findById(uuid)
            .orElseThrow(() ->
                new IllegalArgumentException("Post not found"));
    }

    public @Nullable List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
