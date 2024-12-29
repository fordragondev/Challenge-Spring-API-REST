package dev.fordragon.forohub.api.domain.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository <AppUser, Long> {

    Optional<AppUser> findById(Long id);

    UserDetails findByLogin(String login);
}
