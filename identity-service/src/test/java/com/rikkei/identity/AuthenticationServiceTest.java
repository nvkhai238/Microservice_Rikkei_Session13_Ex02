package com.rikkei.identity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class AuthenticationServiceTest {
    @Test
    void registerStoresBcryptHashAndUserRole() {
        UserRepository users = mock(UserRepository.class);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        when(users.save(any(User.class))).thenAnswer(call -> call.getArgument(0));
        AuthenticationService service = new AuthenticationService(users, encoder);

        UserResponse response = service.register(new RegisterRequest(" alice ", "password123"));
        ArgumentCaptor<User> saved = ArgumentCaptor.forClass(User.class);
        verify(users).save(saved.capture());
        assertEquals("alice", response.username());
        assertEquals("USER", response.role());
        assertNotEquals("password123", saved.getValue().getPassword());
        assertTrue(encoder.matches("password123", saved.getValue().getPassword()));
    }
}
