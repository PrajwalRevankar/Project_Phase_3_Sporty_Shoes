package com.sportyshoes.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshoes.model.Category;
import com.sportyshoes.model.Report;
import com.sportyshoes.model.Shoes;
import com.sportyshoes.model.User;
import com.sportyshoes.repository.CatRepository;
import com.sportyshoes.repository.ReportRepository;
import com.sportyshoes.repository.ShoeRepository;
import com.sportyshoes.repository.UserRepository;

@Service
public class CRUDService {
	
	@Autowired
	ShoeRepository shoerepo;
	@Autowired
	CatRepository catrepo;
	@Autowired
	UserRepository userrepo;
	@Autowired
	ReportRepository reportrepo;
	
	public Shoes saveShoe(Shoes shoe) {
		return shoerepo.save(shoe);
	}
	
	public List<Shoes> listShoe(){
		List<Shoes> slist = new ArrayList<Shoes>();
		shoerepo.findAll().forEach(shoe->slist.add(shoe));
		return slist; 
	}
	
	public void deleteShoe(int id) {
		shoerepo.deleteById(id);
	}
	
	public Category saveCategory(Category cat) {
		return catrepo.save(cat);
	}
	
	public List<Category> listcategory(){
		List<Category> clist = new ArrayList<Category>();
		catrepo.findAll().forEach(cat->clist.add(cat));
		return clist;
	}
	
	public List<User> listuser(){
		List<User> ulist = new ArrayList<User>();
		userrepo.findAll().forEach(user->ulist.add(user));
		return ulist;
		
	}

	public List<User> findByName(String name) {
		// TODO Auto-generated method stub
		return userrepo.findByNameLike("%"+name+"%");
		}

	public List<Report> listreport() {
		List<Report> rlist = new ArrayList<Report>();
		reportrepo.findAll().forEach(rep->rlist.add(rep));
		return rlist;
	}
	
	public List<Report> findByCategory(String category){
		return reportrepo.findByCategory(category);
	}
	
	public List<Report> findByDop(Date date){
		return reportrepo.findByDop(date);
	}
}

