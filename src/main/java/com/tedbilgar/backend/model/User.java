package com.tedbilgar.backend.model;


import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Set;

@Entity
@Table(name = "game_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name="email")
    @Email(message = "Please put your email.")
    @NotEmpty(message = "Need not empty email.")
    private String email;

    @Column(name = "username")
    @NotEmpty(message = "Need username")
    private String username;

    @Column(name = "password")
    @Length(min = 6,message = "At least 6 simbols")
    @NotEmpty(message = "Please provide valid password")
    private String password;

    @Column(name = "active")
    private boolean active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
