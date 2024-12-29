package dev.fordragon.forohub.api.domain.appuser;

import dev.fordragon.forohub.api.infra.validation.Unique;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AppUserCreationDTO(
        @NotBlank
        @Unique(fieldName = "login", entityClass = AppUser.class)
        String login,
        @NotBlank(message = "{appUserCreationDTO.email.NotBlank}")
        @Email(message = "{appUserCreationDTO.email.Email}")
        @Unique(fieldName = "email", entityClass = AppUser.class, message = "{appUserCreationDTO.email.Unique}")
        String email,
        @NotBlank
        String password
) {
}