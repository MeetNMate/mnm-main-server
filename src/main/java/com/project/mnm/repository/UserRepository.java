package com.project.mnm.repository;

import com.project.mnm.domain.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
//    private final EntityManager entityManager;
//
//    @Autowired
//    public UserRepository(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    public User save(User user) {
//        entityManager.persist(user);
//        return null;
//    }
//
//    public Optional<User> findById(Long id) {
//        User user = entityManager.find(User.class, id);
//        return Optional.ofNullable(user);
//    }

//    public User findByEmail(String email)  throws NotFoundException {
//        try {
//            User user = entityManager.createQuery("select u from User u where u.email = :email", User.class).setParameter("email", email).getSingleResult();
//            return user;
//        }
//        catch (Exception e) {
//            return null;
//        }
//    }

//    public List<User> findAll() {
//        List<User> users = (List<User>) entityManager.createQuery("select u from User u", User.class).getResultList();
//        return users;
//    }

}
