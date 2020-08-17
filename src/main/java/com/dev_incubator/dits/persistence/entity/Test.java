package com.dev_incubator.dits.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "test")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToOne(optional = false)
    @JoinColumn(name = "topic_id", nullable = false, foreignKey = @ForeignKey(name = "test_topic_FK"))
    private Topic topic;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "test")
    private Set<Question> questions = new HashSet<>();
}
