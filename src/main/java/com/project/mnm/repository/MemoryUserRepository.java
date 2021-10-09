package com.project.mnm.repository;

import com.project.mnm.domain.User;

import java.util.*;

public class MemoryUserRepository implements UserRepository{
    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public User save(User user) {
        user.setId(++sequence);
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        store.values().stream().filter(user -> user.getEmail().equals(email)).findAny();
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }
}
