package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;


public interface UserService {

    List<User> getUsers();

    void saveUser(User user);

    User getUser(Long id);

    void updateUser(User user);

    void deleteUser(Long id);

    User findUserByUsername(String username);
}
