package com.dev_incubator.dits.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "statistic")
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@CreationTimestamp
    //private LocalDateTime date;
    private Timestamp date;
    private boolean correct;
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "statistic_user_FK"))
    private User user;
    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id", nullable = false, foreignKey = @ForeignKey(name = "statistic_question_FK"))
    private Question question;
}
