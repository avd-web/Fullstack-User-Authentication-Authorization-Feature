package com.avd.security.auth;

import com.avd.security.user.Role;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String firstname;
  private String lastname;
  @NotNull
  @Size(min = 10, max = 30)
  private String email;
  @NotNull
  @Size(min = 8, max = 100)
  private String password;
  @NotNull
  private Role role;
}
