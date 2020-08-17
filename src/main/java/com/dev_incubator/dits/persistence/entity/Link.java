package com.dev_incubator.dits.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "link")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    @ManyToOne(optional = false)
    @JoinColumn(name = "literature_id", nullable = false, foreignKey = @ForeignKey(name = "link_literature_FK"))
    private Literature literature;
}
