package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
	private final UserService userService;
	private final RoleService roleService;
	private final UserValidator userValidator;

	public AdminController(UserService userService, RoleService roleService, UserValidator userValidator) {
		this.userService = userService;
		this.roleService = roleService;
		this.userValidator = userValidator;
	}

	@GetMapping()
	public String showAllUsers(ModelMap model) {
		model.addAttribute("users", userService.getUsers());
		return "admin";
	}

	@GetMapping("/addNewUser")
	public String getFormForCreate(Model model, @ModelAttribute("user") User user) {
		List<Role> roles = roleService.getAllRoles();
		model.addAttribute("roles", roles);
		return "newUser";
	}

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			return "newUser";
		}
		userService.saveUser(user);
		return "redirect:/admin";
	}

	@PostMapping("/updateUser")
	public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "user-info";
		}
		userService.updateUser(user);
		return "redirect:/admin";
	}

	@PatchMapping("/{id}")
	public String getFormForUpdate(@PathVariable("id") Long id, Model model) {
		User user = userService.getUser(id);
		model.addAttribute("user", user);
		List<Role> roles = roleService.getAllRoles();
		model.addAttribute("roles", roles);
		return "user-info";
	}

	@DeleteMapping("/{id}/delete")
	public String deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return "redirect:/admin";
	}
}