package com.sportyshoes.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshoes.model.Category;
import com.sportyshoes.model.Shoes;
import com.sportyshoes.model.User;
import com.sportyshoes.repository.ShoeRepository;
import com.sportyshoes.service.CRUDService;
@Controller
public class CRUDController {
	
	@Autowired
	ShoeRepository srepo;
	@Autowired
	CRUDService cservice;
	
	
	@GetMapping("/addshoe")
	private String SaveShoes(Model model) {
		Shoes shoe = new Shoes();
		model.addAttribute("shoe",shoe);
		return "AddShoe";
	}
	
	@PostMapping("/addshoes")
	private String saveShoe(@ModelAttribute Shoes shoe, Model model) {
		model.addAttribute("shoename", shoe.getShoename());
		model.addAttribute("size", shoe.getSize());
		srepo.save(shoe);
		return "ViewShoe";
	}
	
	
	@GetMapping("/viewshoe")
	private String listShoe(Model model) {
		model.addAttribute("slist", cservice.listShoe());
		return "ViewShoe";
	}
	@GetMapping("/deleteshoe/{id}")
	private String deleteShoe(@PathVariable("id") int id, Model model){
		cservice.deleteShoe(id);
		model.addAttribute("slist", cservice.listShoe());
		return "ViewShoe";
		}
	
	@GetMapping("/addcat")
	private String SaveCategory(Model model) {
		Category cat = new Category();
		model.addAttribute("cat",cat);
		return "AddCategory";
	}
	
	@PostMapping("/addcategories")
	private String saveCategory(@ModelAttribute Category cat, Model model) {
		model.addAttribute("categoryName", cat.getCategoryName());
		cservice.saveCategory(cat);
		model.addAttribute("clist", cservice.listcategory());
		return "ViewCategory";
	}
	
	@GetMapping("/viewcategory")
	private String listCategory(Model model) {
		model.addAttribute("clist", cservice.listcategory());
		return "ViewCategory";
	}
	
	@GetMapping("/viewuser")
	private String listUser(Model model) {
		model.addAttribute("ulist", cservice.listuser());	
		return "ViewUser";
	}
	
	@PostMapping("/searchuser")
	private String searchUser(@RequestParam(defaultValue="name") String name, Model model) {
		model.addAttribute("udetails",cservice.findByName(name));
		return "SearchResult";
		
	}
	
	@GetMapping("/viewreport")
	private String listReport(Model model) {
		model.addAttribute("rlist", cservice.listreport());
		return "ViewReport";	
	}
	@PostMapping("/searchCat")
	private String searchByCategory(@RequestParam(defaultValue="category") String category, Model model) {
		model.addAttribute("cdetails", cservice.findByCategory(category));
		return "CategoryResult";
	}
	@PostMapping("/searchDate")
	private String searchByDate(@RequestParam(defaultValue="date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, Model model) {
		model.addAttribute("searchdate", cservice.findByDop(date));
		return "DateResult";
	}
}
