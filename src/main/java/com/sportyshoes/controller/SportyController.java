package com.sportyshoes.controller;




import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.expression.Messages;

import com.sportyshoes.model.Admin;
import com.sportyshoes.repository.SportyRepository;
import com.sportyshoes.service.SportyService;

@Controller
public class SportyController {
	
	@Autowired
	SportyService sportyserv;
	@Autowired
	SportyRepository sportyrepo;
	
	@GetMapping("/")
	private String Login(Model model) {
		Admin admin = new Admin();
		model.addAttribute("admin", admin);
		return "login";
	}
	
	@PostMapping("/userlogin")
	private String userlogin(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session, ModelMap modelMap) {
		Admin ulogin = sportyserv.Login(username, password);
		
		if(ulogin!=null) {
			String uname = ulogin.getUsername();
			String upass = ulogin.getPassword();
			
			if(username.equalsIgnoreCase(uname) && password.equals(upass)) {
				session.setAttribute("username", username);
				return "home";
			}
			else {
				modelMap.put("Error!!", "Enter correct username and password");
				return "redirect:/";
			
			}
		}
		else {
			modelMap.put("Error!!", "Invalid account");
			return "redirect:/";
		}
				
		
	}
	
	@GetMapping("/logout")
	private String userlogout(HttpSession session) {
		session.removeAttribute("username");
		session.invalidate();
		return "redirect:/";
		
	}
	
	@GetMapping("/cpassword")
	private String changePassword() {
		return "password";
	}
	
	@PostMapping("/changepassword")
	private String password(@RequestParam("oldpassword") String oldpassword, @RequestParam("newpassword") String newpassword, HttpSession session) {
		String uname = (String) session.getAttribute("username");
		Admin currentAdmin = sportyserv.getUserByUsername(uname);
		if(oldpassword.equals(currentAdmin.getPassword())) {
			currentAdmin.setPassword(newpassword);
			sportyrepo.save(currentAdmin);
			return "home";
		}
		else {
			return "password";
		}
	}
	
	@GetMapping("/back")
	private String goBack() {
		return "home";
	}
	
	@GetMapping("/backToAddShoe")
	private String backToAddShoe() {
		return "AddShoe";
	}
}
