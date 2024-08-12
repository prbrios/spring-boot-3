package prbrios.nfeapi.controllers;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import prbrios.nfeapi.entities.UserEntity;
import prbrios.nfeapi.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "basicAuth")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USUARIO_LISTAR')")
    public ResponseEntity<List<UserEntity>> list(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(this.service.list());
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USUARIO_VER')")
    public ResponseEntity<UserEntity> view(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id) {
        return ResponseEntity.ok(this.service.find(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USUARIO_INSERIR')")
    public ResponseEntity<UserEntity> insert(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserEntity user) {
        return ResponseEntity.created(null).body(this.service.insert(user));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USUARIO_ALTERAR')")
    public ResponseEntity<UserEntity> update(@AuthenticationPrincipal UserDetails userDetails, @RequestBody UserEntity user, @PathVariable("id") Long id) {

        System.out.println("asdasd");
        return ResponseEntity.created(null).body(this.service.update(user, id));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USUARIO_DELETAR')")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("id") Long id) {
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
