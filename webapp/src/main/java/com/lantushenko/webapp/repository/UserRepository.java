package com.lantushenko.webapp.repository;

import com.lantushenko.webapp.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    @EntityGraph(value = "User.roles", type = EntityGraph.EntityGraphType.FETCH)
    User findByUsername(String username);
}
