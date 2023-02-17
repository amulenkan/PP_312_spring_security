package ru.kata.spring.boot_security.demo.util;


import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;


@Component
public class Init {
    private final UserService userService;
    private final RoleService roleService;

    public Init(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void myInitMethod() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleService.addRole(roleAdmin);
        roleService.addRole(roleUser);

        Set<Role> adminSet = new HashSet<>();
        adminSet.add(roleAdmin);
        adminSet.add(roleUser);

        Set<Role> userSet = new HashSet<>();
        userSet.add(roleUser);

        User admin = new User("Ivan", "Ivanov", 43L, "ivan@mail.ru",
                "ivan", "100", adminSet);
        User user1 = new User("Mariya", "Petrova", 32L, "masha@mail.ru",
                "masha", "100", userSet);

        userService.saveUser(admin);
        userService.saveUser(user1);
    }
}
