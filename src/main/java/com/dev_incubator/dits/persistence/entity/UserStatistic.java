package com.dev_incubator.dits.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Immutable
@Subselect(
        value = "SELECT row_number() over (ORDER BY u.id)   AS id, " +
                "u.id                                       AS user_id, " +
                "t.id                                       AS test_id, " +
                "count(DISTINCT  s.date)                    AS amountPass, " +
                "sum(s.correct)                             AS correct, " +
                "count(s.correct)                           AS total " +
                "FROM user u " +
                "RIGHT JOIN statistic s " +
                "ON u.id = s.user_id " +
                "LEFT JOIN question q " +
                "ON q.id = s.question_id " +
                "LEFT JOIN test t " +
                "ON t.id = q.test_id " +
                "GROUP BY u.id, t.id")
@Synchronize({"Statistic", "Test", "Question", "User"})
public class UserStatistic {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "test_id", nullable = false)
    private Test test;
    private Integer amountPass;
    private Integer correct;
    private Integer total;
}
