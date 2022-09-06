package com.onlinefoodchat.controller;

import java.io.IOException;

import javax.mail.Multipart;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.onlinefoodchat.entity.MenuEntity;
import com.onlinefoodchat.service.RestoService;

@Controller
public class RestoController {

	ModelAndView modelAndView =new ModelAndView();
	@Autowired
	RestoService restoService;
	@GetMapping("/checkResto")
	public ModelAndView CheckResto(HttpSession session) {
		System.out.println(session.getAttribute("email"));
		if(restoService.check(session)) {
			System.out.println("done");
			 modelAndView.setViewName("AddResto");
			 return modelAndView;
		}
				modelAndView.setViewName("DishMenu");
				return 	modelAndView;	
	}
	
	@PostMapping("/addResto")
	public ModelAndView addResto(@RequestParam("restoName")String restoName) {
		System.out.println("add="+restoName);
		
		if(restoService.addRestoName(restoName)) {
		modelAndView.setViewName("DishMenu");
			return modelAndView;
		}
		modelAndView.setViewName("AddResto");
		return modelAndView;
	}
	
	@SuppressWarnings(value = { "unchecked", "deprecated" })
	@RequestMapping(value = { "/addMenu" }, consumes = {"multipart/form-data"}) 
	public ModelAndView addMenu(@ModelAttribute MenuEntity menuEntity,@RequestParam("menuImage") MultipartFile multipart) throws IOException {
		System.out.println(multipart);
		boolean addMenuData = restoService.addMenuData(menuEntity,multipart);
		System.out.println(addMenuData);
		return modelAndView;
	}

}
