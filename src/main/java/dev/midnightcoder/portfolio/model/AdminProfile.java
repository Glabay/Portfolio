package dev.midnightcoder.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Glabay | The Midnight Coder
 * @project Portfolio
 * @social Discord: Glabay
 * @website <a href="https://midnightcoder.dev">Midnight Coder</a>
 * @since 2026-06-14
 */
@Entity
@Getter @Setter
@Table(name = "admin_profiles")
public class AdminProfile extends Profile {}
