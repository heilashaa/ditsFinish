package com.dev_incubator.dits.persistence.repository;

import com.dev_incubator.dits.persistence.entity.Role;
import com.dev_incubator.dits.persistence.entity.enumerated.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleEnum roleEnum);
}
