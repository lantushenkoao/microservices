package com.lantushenko.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NamedEntityGraph(name = "User.roles", attributeNodes = @NamedAttributeNode("roles"))
@Where(clause = "is_deleted = false")
@ToString(exclude = {"password"})
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @NotEmpty(message = "Введите имя")
    @Length(max = 256, message = "Имя должно быть не длиннее {max} символов")
    private String name;

    @NotEmpty(message = "Введите логин")
    @Length(max = 20, message = "Логин должен быть не длиннее {max} символов")
    private String username;

    @Length(max = 50, message = "Электронный адрес должен быть до {max} символов")
    @Email
    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    boolean isDeleted;

    public boolean isTransient(){
        return getId() == null;
    }
}
