package com.rikkei.identity;

import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {
    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserResponse register(RegisterRequest request) {
        String username = request.username().trim();
        if (users.existsByUsername(username)) {
            throw new DuplicateUsernameException();
        }
        User user = new User(UUID.randomUUID().toString(), username,
            passwordEncoder.encode(request.password()), "USER");
        return UserResponse.from(users.save(user));
    }
}
