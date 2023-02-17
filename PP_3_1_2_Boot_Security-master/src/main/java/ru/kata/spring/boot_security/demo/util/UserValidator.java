package ru.kata.spring.boot_security.demo.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.util.Optional;

@Component
public class UserValidator implements Validator {
    private final UserServiceImpl userService;

    public UserValidator(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        Optional<User> result = userService.loadByUsernameToOptional(user.getUsername());
        if (result.isPresent()) {
            errors.rejectValue("username", "", "This username is in use by another user");
        }
    }
}
