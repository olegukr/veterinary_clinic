package org.factoriaf5.vcp.controller;

import org.factoriaf5.vcp.dto.UserDTO;
import org.factoriaf5.vcp.model.User;
import org.factoriaf5.vcp.model.UserType;
import org.factoriaf5.vcp.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Инициализация Mockito
    }

    @Test
    void testRegisterUser_Success() {
        // Тест успешной регистрации пользователя
        UserDTO userDTO = new UserDTO("testuser", "password", "USER", "123456789");
        User user = new User("testuser", "password", UserType.USER, "123456789");

        when(userService.registerUser(any(User.class))).thenReturn(user);

        ResponseEntity<?> response = userController.registerUser(userDTO);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(user, response.getBody());
        verify(userService).registerUser(any(User.class));
    }

    @Test
    void testRegisterUser_MissingFields() {
        // Тест регистрации с отсутствующими обязательными полями
        UserDTO userDTO = new UserDTO("", "", "", null);

        ResponseEntity<?> response = userController.registerUser(userDTO);

        assertEquals(400, response.getStatusCode().value());
        assertTrue(response.getBody().toString().contains("El nombre de usuario es obligatorio"));
    }

    @Test
    void testRegisterUser_InvalidRole() {
        // Тест регистрации с неверным типом роли
        UserDTO userDTO = new UserDTO("testuser", "password", "INVALID_ROLE", "123456789");

        ResponseEntity<?> response = userController.registerUser(userDTO);

        assertEquals(400, response.getStatusCode().value());
        assertTrue(response.getBody().toString().contains("El rol del usuario es inválido"));
    }

    @Test
    void testLoginUser_Success() {
        // Тест успешного входа пользователя
        when(userService.authenticate("testuser", "password")).thenReturn(true);

        ResponseEntity<String> response = userController.loginUser("testuser", "password");

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Inicio de sesión exitoso", response.getBody());
    }

    @Test
    void testLoginUser_Failure() {
        // Тест неуспешного входа пользователя
        when(userService.authenticate("testuser", "wrongpassword")).thenReturn(false);

        ResponseEntity<String> response = userController.loginUser("testuser", "wrongpassword");

        assertEquals(401, response.getStatusCode().value());
        assertEquals("Nombre de usuario o contraseña incorrectos", response.getBody());
    }

    @Test
    void testGetUserByUsername_Found() {
        // Тест успешного поиска пользователя по имени
        User user = new User("testuser", "password", UserType.USER, "123456789");

        when(userService.findUserByUsername("testuser")).thenReturn(Optional.of(user));

        ResponseEntity<?> response = userController.getUserByUsername("testuser");

        assertEquals(200, response.getStatusCode().value());
        assertEquals(user, response.getBody());
    }

    @Test
    void testGetUserByUsername_NotFound() {
        // Тест, если пользователь не найден
        when(userService.findUserByUsername("testuser")).thenReturn(Optional.empty());

        ResponseEntity<?> response = userController.getUserByUsername("testuser");

        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void testGetAllUsers() {
        // Тест получения всех пользователей
        User user1 = new User("user1", "password1", UserType.USER, "123456789");
        User user2 = new User("user2", "password2", UserType.ADMIN, "987654321");

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        ResponseEntity<?> response = userController.getAllUsers();

        assertEquals(200, response.getStatusCode().value());
        List<?> users = (List<?>) response.getBody();
        assertEquals(2, users.size());
    }

    @Test
    void testDeleteUser_Success() {
        // Тест успешного удаления пользователя
        doNothing().when(userService).deleteUser(1L);

        ResponseEntity<?> response = userController.deleteUser(1L);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Usuario eliminado exitosamente", response.getBody());
    }

    @Test
    void testDeleteUser_NotFound() {
        // Тест, если пользователь для удаления не найден
        doThrow(new IllegalArgumentException("Usuario con ID 1 no encontrado"))
                .when(userService).deleteUser(1L);

        ResponseEntity<?> response = userController.deleteUser(1L);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Usuario con ID 1 no encontrado", response.getBody());
    }

    @Test
    void testUpdateUser_Success() {
        // Тест успешного обновления пользователя
        User userDetails = new User("updateduser", "newpassword", UserType.ADMIN, "987654321");
        User updatedUser = new User("updateduser", "newpassword", UserType.ADMIN, "987654321");

        when(userService.updateUser(1L, userDetails)).thenReturn(updatedUser);

        ResponseEntity<?> response = userController.updateUser(1L, userDetails);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(updatedUser, response.getBody());
    }

    @Test
    void testUpdateUser_NotFound() {
        // Тест, если пользователь для обновления не найден
        User userDetails = new User("updateduser", "newpassword", UserType.ADMIN, "987654321");

        doThrow(new IllegalArgumentException("Usuario con ID 1 no encontrado"))
                .when(userService).updateUser(1L, userDetails);

        ResponseEntity<?> response = userController.updateUser(1L, userDetails);

        assertEquals(400, response.getStatusCode().value());
        assertEquals("Usuario con ID 1 no encontrado", response.getBody());
    }
}
