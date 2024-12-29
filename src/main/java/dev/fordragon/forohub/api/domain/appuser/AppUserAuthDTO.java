package dev.fordragon.forohub.api.domain.appuser;

import jakarta.validation.constraints.NotBlank;

public record AppUserAuthDTO(
        @NotBlank
        String login,
        @NotBlank
        String password
) {
}
