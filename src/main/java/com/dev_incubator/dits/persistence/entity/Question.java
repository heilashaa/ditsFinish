package com.dev_incubator.dits.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @ManyToOne(optional = false)
    @JoinColumn(name = "test_id", nullable = false, foreignKey = @ForeignKey(name = "question_test_FK"))
    private Test test;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question")
    private Set<Literature> literature = new HashSet<>();
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question")
    private Set<Answer> answers = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private Set<Statistic> statistics = new HashSet<>();
}





