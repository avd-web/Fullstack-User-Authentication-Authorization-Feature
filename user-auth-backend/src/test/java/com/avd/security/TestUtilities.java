package com.avd.security;

import com.avd.security.user.Role;
import com.avd.security.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestUtilities {
    public static User createMockUser1() {
        //Arrange
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() {
        };
        //Arrange testable input-values for user1
        String TestEmail = "user1@mail.com";
        String TestFirstname = "user1Firstname";
        String TestLastname = "user1Lastname";
        String TestPassword = passwordEncoder.encode("password");
        Role TestRole = Role.USER;
        //Return User object via builder
        return User.builder().email(TestEmail).firstname(TestFirstname).lastname(TestLastname).password(TestPassword).role(TestRole).build();
    }
}
