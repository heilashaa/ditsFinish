package com.dev_incubator.dits.persistence.repository;

import com.dev_incubator.dits.persistence.entity.PersonalStatistic;
import com.dev_incubator.dits.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalStatisticRepository extends JpaRepository<PersonalStatistic, Long> {

    Optional<List<PersonalStatistic>> findAllByUser(User user);
}
