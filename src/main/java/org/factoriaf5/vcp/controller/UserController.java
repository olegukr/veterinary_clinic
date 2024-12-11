package org.factoriaf5.vcp.controller;

import java.util.Optional;

import org.factoriaf5.vcp.dto.UserDTO;
import org.factoriaf5.vcp.model.User;
import org.factoriaf5.vcp.model.UserType;
import org.factoriaf5.vcp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        if (userDTO.username() == null || userDTO.username().isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre de usuario es obligatorio");
        }
        if (userDTO.password() == null || userDTO.password().isEmpty()) {
            return ResponseEntity.badRequest().body("La contraseña es obligatoria");
        }
        if (userDTO.usertype() == null || userDTO.usertype().isEmpty()) {
            return ResponseEntity.badRequest().body("El rol del usuario es obligatorio");
        }
    
        User user;
        try {
            user = new User(
                userDTO.username(),
                userDTO.password(),
                UserType.valueOf(userDTO.usertype().toUpperCase()),
                userDTO.phone()
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("El rol del usuario es inválido");
        }
    
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestParam String username, @RequestParam String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre de usuario y la contraseña son obligatorios");
        }

        if (userService.authenticate(username, password)) {
            return ResponseEntity.ok("Inicio de sesión exitoso");
        } else {
            return ResponseEntity.status(401).body("Nombre de usuario o contraseña incorrectos");
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        if (username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().body("El nombre de usuario es obligatorio");
        }

        Optional<User> user = userService.findUserByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}

