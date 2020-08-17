package com.dev_incubator.dits.service.interfaces;

import com.dev_incubator.dits.service.dto.UserDto;
import com.dev_incubator.dits.service.dto.UserListDto;

import java.util.List;

public interface UserService{

    List<UserListDto> getAllUsers();

    boolean saveUser(UserDto userDto);

    Integer countBlockUsers();

    boolean changeBlockStatus(Long id);

    UserDto getUserById(Long id);

    UserDto getUserByLogin(String login);
}
