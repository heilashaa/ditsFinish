package com.dev_incubator.dits.persistence.repository;

import com.dev_incubator.dits.persistence.entity.Question;
import com.dev_incubator.dits.persistence.entity.Statistic;
import com.dev_incubator.dits.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
    //Y
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Statistic set correct = :correct where date = :date and " +
            "user = :user and question = :question")
    void changeCorrectValue(@Param("date") Timestamp date,
                            @Param("user") User user,
                            @Param("question") Question question,
                            @Param("correct") boolean correct);
    List<Statistic> findAllByDate(Timestamp date);
    @Query("from Statistic where date = ?1")
    List<Statistic> getAllByDate(Timestamp date);
}
