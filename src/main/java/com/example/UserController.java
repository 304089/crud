package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/index")
	public String index(Model model) {
		List<User> user = userRepository.findAll();
		model.addAttribute("users",user);
		return "index";
	}
	
	@GetMapping("/form")
	public String newUser(Model model) {
		model.addAttribute(new User());
		return "createForm";
	}
	
	@PostMapping("/form")
	public String create(User user) {
		userRepository.save(user);
		return "redirect:/index";
	}
	
	@GetMapping("/form/{id}")
	public String edit(@PathVariable int id, Model model) {
		User user = userRepository.getById(id);
		model.addAttribute("user", user);
		return "editForm";
	}
	
	@PostMapping("/form/{id}")
	public String update(@PathVariable int id, User user) {
		user.setId(id);
		userRepository.save(user);
		return "redirect:/index";
	}
	
	@PostMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		userRepository.deleteById(id);
		return "redirect:/index";
	}
}
