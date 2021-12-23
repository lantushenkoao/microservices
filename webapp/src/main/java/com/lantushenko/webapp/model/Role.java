package com.lantushenko.webapp.model;

import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    @NotNull
    @Size(max = 100)
    private String name;
}