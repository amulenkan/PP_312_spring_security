package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RoleService {

    Role findRoleById(Integer id);

    List<Role> getAllRoles();

    void addRole(Role role);


}
