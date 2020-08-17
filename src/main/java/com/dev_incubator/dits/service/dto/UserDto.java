package com.dev_incubator.dits.service.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
public class UserDto {

    private Long id;
    @NotEmpty(message = "{user.first.name.not.empty}")
    @Size(min = 2, max = 25, message = "{user.first.name.size}")
    private String firstName;
    @NotEmpty(message = "{user.middle.name.not.empty}")
    @Size(min = 2, max = 25, message = "{user.middle.name.size}")
    private String middleName;
    @NotEmpty(message = "{user.last.name.not.empty}")
    @Size(min = 2, max = 25, message = "{user.last.name.size}")
    private String lastName;
    @NotEmpty(message = "{user.login.not.empty}")
    @Size(min = 2, max = 20, message = "{user.login.size}")
    private String login;
    @NotEmpty(message = "{user.password.not.empty}")
    @Size(min = 5, max = 60, message = "{user.password.size}")
    private String password;
    @NotEmpty(message = "{user.email.not.empty}")
    @Size(min = 3, max = 50, message = "{user.email.size}")
    @Email(message = "{user.email.email}")
    private String email;
    @NotNull(message = "{user.enabled.not.empty}")
    private boolean enabled;
    @NotEmpty(message = "{user.roles.not.empty}")
    private Set<String> roles;
}
