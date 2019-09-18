package com.dpc.Scolarity.Controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dpc.Scolarity.Repository.ClassesRepository;
import com.dpc.Scolarity.Repository.StatEleveRepository;
import com.dpc.Scolarity.Repository.StatistiqueRepository;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "api/statistique")
public class StatistiqueController {
	
	@Autowired
	StatistiqueRepository statrepo;
	@Autowired
	StatEleveRepository  statElvRepo;
	@Autowired
	ClassesRepository classrepo;
	
	@RequestMapping(value = "/getNmbrePenaltByClasse", method = RequestMethod.GET )
	public @ResponseBody List<Object> getNmbrePenaltByClasse(Model model ) throws ParseException {
		return	this.statrepo.countPenalitesByClasse();
	}
	
	@RequestMapping(value = "/getNbrEleveByRegion", method = RequestMethod.GET )
	public @ResponseBody List<Object> countEleveByRegion(Model model ) throws ParseException {
		return	this.statElvRepo.countElevesParRegion();
	}
	
	@RequestMapping(value = "/getNbrClassByRegion", method = RequestMethod.GET )
	public @ResponseBody List<Object> countClassesByRegion(Model model ) throws ParseException {
		return	this.classrepo.countClassesParRegion();
	}
	

}
