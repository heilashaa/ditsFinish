package com.dev_incubator.dits.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserListDto {
    private Long id;
    private String fullName;
    private String login;
    private String email;
    private boolean enabled;
    private Set<String> roles;
}
