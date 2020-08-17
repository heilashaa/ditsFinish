package com.dev_incubator.dits.service.dto.mapper;

import com.dev_incubator.dits.persistence.entity.Role;
import com.dev_incubator.dits.persistence.entity.User;
import com.dev_incubator.dits.persistence.entity.enumerated.RoleEnum;
import com.dev_incubator.dits.service.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Component
public class UserMapper {

    public UserDto toDto(final User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setMiddleName(user.getMiddleName());
        dto.setLastName(user.getLastName());
        dto.setLogin(user.getLogin());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setEnabled(user.isEnabled());
        dto.setRoles(user.getRoles().stream().map(role -> role.getName().toString()).collect(Collectors.toSet()));
        return dto;
    }

    public User fromDto(final UserDto dto, List<Role> roles) {
        User user = new User();
        if (nonNull(dto.getId())) {
            user.setId(dto.getId());
        }
        user.setFirstName(dto.getFirstName());
        user.setMiddleName(dto.getMiddleName());
        user.setLastName(dto.getLastName());
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setEnabled(dto.isEnabled());
        Set<Role> userRoles = dto.getRoles().stream()
                .map(role ->
                        roles.stream()
                                .filter(r -> Objects.equals(r.getName(), RoleEnum.valueOf(role)))
                                .findFirst().orElse(new Role(RoleEnum.valueOf(role)))
                )
                .collect(Collectors.toSet());
        user.setRoles(userRoles);
        return user;
    }
}
