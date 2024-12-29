package dev.fordragon.forohub.api.domain.appuser;

public record AppUserResponseDTO(
        long id,
        String login,
        String email
) {

}
