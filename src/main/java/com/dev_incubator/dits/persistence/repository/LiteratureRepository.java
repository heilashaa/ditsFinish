package com.dev_incubator.dits.persistence.repository;

import com.dev_incubator.dits.persistence.entity.Literature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LiteratureRepository extends JpaRepository<Literature, Long> {
}
