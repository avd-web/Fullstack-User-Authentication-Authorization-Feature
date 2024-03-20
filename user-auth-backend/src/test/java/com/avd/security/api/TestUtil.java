package com.avd.security.api;

import com.avd.security.user.Role;
import com.avd.security.user.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestUtil {
    public static User createMockUser1() {
        //Arrange
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() {
        };
        //Arrange testable input-values for user1
        String TestEmail = "user1@mail.com";
        String TestFirstname = "user1Firstname";
        String TestLastname = "user1Lastname";
        String TestPassword = passwordEncoder.encode("password");
        //Remove "Role TestRole = Role.USER;" here.
        Role TestRole = Role.USER;
        //Return User object via builder
        return User.builder().email(TestEmail).firstname(TestFirstname).lastname(TestLastname).password(TestPassword).role(TestRole).build();
    }

    public static User createMockUser2() {
        //Arrange
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() {
        };
        //Arrange testable input-values for user1
        String TestEmail = "user2@mail.com";
        String TestFirstname = "user2Firstname";
        String TestLastname = "user2Lastname";
        String TestPassword = passwordEncoder.encode("password");
        Role TestRole = Role.USER;
        //Return User object via builder
        return User.builder().email(TestEmail).firstname(TestFirstname).lastname(TestLastname).password(TestPassword).role(TestRole).build();
    }

    public static User createMockManager1() {
        //Arrange
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() {
        };
        //Arrange testable input-values for user1
        String TestEmail = "manager1@mail.com";
        String TestFirstname = "manager1Firstname";
        String TestLastname = "manager1Lastname";
        String TestPassword = passwordEncoder.encode("password");
        Role TestRole = Role.MANAGER;
        //Return User object via builder
        return User.builder().email(TestEmail).firstname(TestFirstname).lastname(TestLastname).password(passwordEncoder.encode(TestPassword)).role(TestRole).build();
    }

    public static User createMockAdmin1() {
        //Arrange
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder() {
        };
        //Arrange testable input-values for user1
        String TestEmail = "admin1@mail.com";
        String TestFirstname = "admin1Firstname";
        String TestLastname = "admin1Lastname";
        String TestPassword = passwordEncoder.encode("password");
        Role TestRole = Role.ADMIN;
        //Return User object via builder
        return User.builder().email(TestEmail).firstname(TestFirstname).lastname(TestLastname).password(passwordEncoder.encode(TestPassword)).role(TestRole).build();
    }
}