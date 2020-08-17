package com.dev_incubator.dits.persistence.repository;

import com.dev_incubator.dits.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);

    List<User> findAllByOrderByIdDesc();

    Integer countByEnabledFalse();
}
