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
        value = "SELECT row_number() over (ORDER BY tp.id)  AS id, " +
                "tp.id                                      AS topic_id, " +
                "COUNT(DISTINCT  s.date)                    AS amountPass, " +
                "SUM(s.correct)                             AS correct, " +
                "COUNT(s.correct)                           AS total " +
                "FROM dits_test.topic tp " +
                "LEFT JOIN dits_test.test t " +
                "ON tp.id = t.id " +
                "LEFT JOIN dits_test.question q " +
                "ON t.id = q.test_id " +
                "LEFT JOIN dits_test.statistic s " +
                "ON q.id = s.question_id " +
                "GROUP BY tp.name")
@Synchronize({"Statistic", "Test", "Question", "Topic"})
public class TopicStatistic {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;
    private Integer amountPass;
    private Integer correct;
    private Integer total;
}
