package com.dev_incubator.dits.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "middle_name", nullable = false)
    private String middleName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(name = "is_enabled", nullable = false)
    private boolean enabled;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "user_id")}, foreignKey = @ForeignKey(name = "role_user_FK"),
            inverseJoinColumns = {@JoinColumn(name = "role_id")}, inverseForeignKey = @ForeignKey(name = "user_role_FK"))
    private Set<Role> roles = new HashSet<>();
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Statistic> statistics = new HashSet<>();
}
