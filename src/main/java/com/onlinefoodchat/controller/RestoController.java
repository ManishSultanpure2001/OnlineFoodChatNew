package com.onlinefoodchat.controller;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchat.entity.MenuEntity;
import com.onlinefoodchat.service.RestoService;

@Controller
public class RestoController {
	int globalId;
	ModelAndView modelAndView = new ModelAndView();
	@Autowired
	RestoService restoService;

	/* Check Resto Register or not */
	@GetMapping("/checkResto")
	public ModelAndView CheckResto(HttpSession session) {
		if (restoService.check(session)) {
			modelAndView.setViewName("AddResto");
			return modelAndView;
		}
		modelAndView.setViewName("DishMenu");
		return modelAndView;
	}

	/* View All Menu */
	@GetMapping("/allMenu")
	public ModelAndView allMenu(HttpSession session) {
		List<MenuEntity> allMenu = restoService.getAllMenu(session);
		modelAndView.addObject("allDish", allMenu);
		modelAndView.setViewName("ShowAllMenu");
		return modelAndView;
	}

	/* Add Resto */
	@PostMapping("/addResto")
	public ModelAndView addResto(@RequestParam("restoName") String restoName) {
		if (restoService.addRestoName(restoName)) {
			modelAndView.setViewName("DishMenu");
			return modelAndView;
		}
		modelAndView.setViewName("AddResto");
		return modelAndView;
	}

	/* Add Dish */
	@PostMapping("/addMenu")
	public ModelAndView addMenu(@ModelAttribute MenuEntity menuEntity,
			@RequestParam("multipart") MultipartFile multipart, HttpSession session) throws IOException {
		boolean addMenuData = restoService.addMenuData(menuEntity, multipart, session);
		List<MenuEntity> allMenu = restoService.getAllMenu(session);
		modelAndView.addObject("allDish", allMenu);
		modelAndView.setViewName("ShowAllMenu");
		return modelAndView;
		 
	}

	/* Edit Dish */
	@GetMapping("/editDish")
	public ModelAndView editMenu(@ModelAttribute("menuId") int id) {
		globalId = id;
		MenuEntity editData = restoService.getEditData(id);
		modelAndView.addObject("data", editData);
		modelAndView.setViewName("EditMenu");
		return modelAndView;
	}

	/* Successfull Edit Update */
	@PostMapping("/updateSuccessful")
	public ModelAndView SuccessUpdate(@ModelAttribute MenuEntity entity,
			@RequestParam("multipart") MultipartFile multipart, HttpSession session) throws IOException {
		restoService.updateData(entity, globalId, multipart, session);
		List<MenuEntity> allMenu = restoService.getAllMenu(session);
		System.out.println("All Menu= "+allMenu);
		modelAndView.addObject("allDish", allMenu);
		modelAndView.setViewName("ShowAllMenu");
		return modelAndView;
	}

	/* Delete Dish */
	@RequestMapping("/deleteDish")
	public ModelAndView deleteDish(@ModelAttribute("menuId") int id, @ModelAttribute("menuImage") String image,
			HttpSession session) {
		restoService.dishDelete(id, image);
		this.allMenu(session);
		modelAndView.setViewName("ShowAllMenu");
		return modelAndView;
	}
}
