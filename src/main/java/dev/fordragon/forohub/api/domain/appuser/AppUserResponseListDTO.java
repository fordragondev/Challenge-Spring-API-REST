package dev.fordragon.forohub.api.domain.appuser;

public record AppUserResponseListDTO(
        long id,
        String login,
        String email
) {
    public AppUserResponseListDTO(AppUser appUser) {
        this(appUser.getId(), appUser.getLogin(), appUser.getEmail());
    }
}
