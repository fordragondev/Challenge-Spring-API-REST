package dev.fordragon.forohub.api.controller;

import dev.fordragon.forohub.api.domain.appuser.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
public class AppUserController {

    @Autowired
    private AppUserRepository appUserRepository;

    @PostMapping
    public ResponseEntity<AppUserResponseDTO> createUser(@RequestBody @Valid AppUserCreationDTO appUserCreationDTO,
                                                           UriComponentsBuilder uriComponentsBuilder) {
       AppUser appUser = appUserRepository.save(new AppUser(appUserCreationDTO));

       AppUserResponseDTO appUserResponseDTO = new AppUserResponseDTO(appUser.getId(), appUser.getLogin(), appUser.getEmail());
       URI url = uriComponentsBuilder.path("/users/{id}").buildAndExpand(appUser.getId()).toUri();
       return ResponseEntity.created(url).body(appUserResponseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<AppUserResponseListDTO>> getListAppUser(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(appUserRepository.findAll(paginacion).map(AppUserResponseListDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserResponseDTO> getAppUser(@PathVariable Long id) {
        AppUser appUser = appUserRepository.getReferenceById(id);
        var appUserResponseDTO = new AppUserResponseDTO(appUser.getId(), appUser.getLogin(), appUser.getEmail());
        return ResponseEntity.ok(appUserResponseDTO);
    }
}

