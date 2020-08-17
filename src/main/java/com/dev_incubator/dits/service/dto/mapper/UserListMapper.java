package com.dev_incubator.dits.service.dto.mapper;

import com.dev_incubator.dits.persistence.entity.User;
import com.dev_incubator.dits.service.dto.UserListDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserListMapper {

    public UserListDto toDto(final User user) {
        UserListDto dto = new UserListDto();
        dto.setId(user.getId());
        String fullName = user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName();
        dto.setFullName(fullName);
        dto.setLogin(user.getLogin());
        dto.setEmail(user.getEmail());
        dto.setEnabled(user.isEnabled());
        dto.setRoles(user.getRoles().stream().map(role -> role.getName().toString()).collect(Collectors.toSet()));
        return dto;
    }
}
