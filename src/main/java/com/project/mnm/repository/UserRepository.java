package com.project.mnm.repository;

import com.project.mnm.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    private final EntityManager entityManager;

    @Autowired
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public User save(User user) {
        entityManager.persist(user);
        return null;
    }

    public Optional<User> findById(Long id) {
        User user = entityManager.find(User.class, id);
        return Optional.ofNullable(user);
    }

    public Optional<User> findByEmail(String email) {
        List<User> users = (List<User>) entityManager.createQuery("select u from User u where u.email = :email", User.class).setParameter("email", email).getResultList();
        return users.stream().findAny();
    }

    public List<User> findAll() {
        List<User> users = (List<User>) entityManager.createQuery("select u from User u", User.class).getResultList();
        return users;
    }

}
