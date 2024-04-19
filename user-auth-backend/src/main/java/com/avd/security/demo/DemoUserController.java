package com.avd.security.demo;

import com.avd.security.user.User;
import com.avd.security.user.UserDTO;
import com.avd.security.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/demo-user")
public class DemoUserController {

    private final UserService userService;

    @GetMapping(value = "/username")
    public String currentUserName(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("User has authorities: " + userDetails.getAuthorities());
        return authentication.getName();
    }

    @GetMapping(value = "/user")
    public ResponseEntity<UserDTO> getCurrentUser(@AuthenticationPrincipal User currentUser) throws RuntimeException{
//        try {
            UserDTO userDTO = userService.getUser(currentUser);
            return ResponseEntity.ok().body(userDTO);
//        }catch (RuntimeException b) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        } catch (Error e) {
////            logger.error("Error fetching profile for user: {}", currentUser.getUsername());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }

    }
}
