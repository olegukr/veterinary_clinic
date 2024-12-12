package org.factoriaf5.vcp.services;

import org.factoriaf5.vcp.model.User;
import org.factoriaf5.vcp.model.UserType;
import org.factoriaf5.vcp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUser_Success() {
        User user = new User("testuser", "password", UserType.USER, "123456789");

        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        User registeredUser = userService.registerUser(user);

        assertEquals(user, registeredUser);
        verify(userRepository).save(user);
    }

    @Test
    void testRegisterUser_UsernameExists() {
        User user = new User("testuser", "password", UserType.USER, "123456789");

        when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(user));
    }

    @Test
    void testFindUserByUsername_Found() {
        User user = new User("testuser", "password", UserType.USER, "123456789");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        Optional<User> foundUser = userService.findUserByUsername("testuser");

        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
    }

    @Test
    void testFindUserByUsername_NotFound() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        Optional<User> foundUser = userService.findUserByUsername("testuser");

        assertFalse(foundUser.isPresent());
    }

    @Test
    void testAuthenticate_Success() {
        User user = new User("testuser", "password", UserType.USER, "123456789");

        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        boolean isAuthenticated = userService.authenticate("testuser", "password");

        assertTrue(isAuthenticated);
    }

    @Test
    void testAuthenticate_Failure() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.empty());

        boolean isAuthenticated = userService.authenticate("testuser", "password");

        assertFalse(isAuthenticated);
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User("user1", "password1", UserType.USER, "123456789");
        User user2 = new User("user2", "password2", UserType.ADMIN, "987654321");

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    @Test
    void testDeleteUser_Success() {
        User user = new User("testuser", "password", UserType.USER, "123456789");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        userService.deleteUser(user.getId());

        verify(userRepository).delete(user);
    }

    @Test
    void testDeleteUser_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userService.deleteUser(1L));
    }

    @Test
    void testUpdateUser_Success() {
        User existingUser = new User("olduser", "oldpassword", UserType.USER, "123456789");

        User updatedDetails = new User("newuser", "newpassword", UserType.ADMIN, "987654321");

        when(userRepository.findById(existingUser.getId())).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        User updatedUser = userService.updateUser(existingUser.getId(), updatedDetails);

        assertEquals("newuser", updatedUser.getUsername());
        assertEquals("newpassword", updatedUser.getPassword());
        assertEquals("ADMIN", updatedUser.getUsertype().name());
        assertEquals("987654321", updatedUser.getPhone());
    }

    @Test
    void testUpdateUser_NotFound() {
        User updatedDetails = new User("newuser", "newpassword", UserType.ADMIN, "987654321");

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> userService.updateUser(1L, updatedDetails));
    }
}
