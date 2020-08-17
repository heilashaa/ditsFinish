package com.dev_incubator.dits.service;

import com.dev_incubator.dits.persistence.entity.Role;
import com.dev_incubator.dits.persistence.repository.RoleRepository;
import com.dev_incubator.dits.service.interfaces.RoleService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Getter
@Setter
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
