package com.dev_incubator.dits.service;

import com.dev_incubator.dits.exception.UserNotFoundException;
import com.dev_incubator.dits.persistence.entity.User;
import com.dev_incubator.dits.persistence.repository.RoleRepository;
import com.dev_incubator.dits.persistence.repository.UserRepository;
import com.dev_incubator.dits.service.dto.UserDto;
import com.dev_incubator.dits.service.dto.UserListDto;
import com.dev_incubator.dits.service.dto.mapper.UserListMapper;
import com.dev_incubator.dits.service.dto.mapper.UserMapper;
import com.dev_incubator.dits.service.interfaces.UserService;
import com.dev_incubator.dits.util.MessageSourceFacade;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@Setter
@Getter
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final UserListMapper userListMapper;

    private final MessageSourceFacade messageSource;

    @Override
    public List<UserListDto> getAllUsers() {
        return userRepository.findAllByOrderByIdDesc().stream()
                .map(userListMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public boolean saveUser(UserDto userDto) {
        User userFromDb = userRepository.findByLogin(userDto.getLogin());
        //edit user
        if (nonNull(userDto.getId())) {
            if (nonNull(userFromDb) && !userFromDb.getId().equals(userDto.getId())) {
                return false;
            }
            if (isNull(userFromDb) || !userFromDb.getId().equals(userDto.getId())) {
                userFromDb = userRepository.findById(userDto.getId()).orElse(new User());
            }
            if (!Objects.equals(userDto.getPassword(), userFromDb.getPassword())) {
                userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            }
            userRepository.save(userMapper.fromDto(userDto, roleRepository.findAll()));
            return true;
        }
        //create user
        if (nonNull(userFromDb)) {
            return false;
        }
        if (isNull(userDto.getRoles())) {
            userDto.setEnabled(false);
            userDto.setRoles(Collections.singleton("USER"));
        }
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(userMapper.fromDto(userDto, roleRepository.findAll()));
        return true;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(messageSource.getMessage("user.not.found")));
        return userMapper.toDto(user);
    }

    @Override
    public UserDto getUserByLogin(String login) {
        return userMapper.toDto(userRepository.findByLogin(login));
    }

    @Override
    public boolean changeBlockStatus(Long id) {
        UserDto userDto = userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(messageSource.getMessage("user.not.found"))));
        if(userDto.isEnabled()){
            userDto.setEnabled(false);
        }else{
            userDto.setEnabled(true);
        }
        userRepository.save(userMapper.fromDto(userDto, roleRepository.findAll()));
        return userDto.isEnabled();
    }

    @Override
    public Integer countBlockUsers() {
        return userRepository.countByEnabledFalse();
    }
}
