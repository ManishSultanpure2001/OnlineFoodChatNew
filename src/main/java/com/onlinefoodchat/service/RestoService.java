package com.onlinefoodchat.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.onlinefoodchat.entity.ClientLogin;
import com.onlinefoodchat.entity.MenuEntity;
import com.onlinefoodchat.repository.ClientRepository;
import com.onlinefoodchat.repository.MenuRepository;

@Service
public class RestoService {
	InputStream inputStreamImage;
	private FileOutputStream fileOutputStreamImage;
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	ClientLogin findByClientEmail;
	
	/*
	 * Check Resto Name
	 */	
	public boolean check(HttpSession request) {
//		System.out.println(request.getSession().getAttribute("clientId"));
		System.out.println("Email="+request.getAttribute("email"));
		findByClientEmail = this.clientRepository.findByClientEmail((String)request.getAttribute("email"));
		System.out.println(findByClientEmail.getClientPlan());
		System.out.println(findByClientEmail.getRestoName());
		if(findByClientEmail.getRestoName()==null)
		return true;
		return false;
	}
	
	/* Add Resto Name */
	public boolean addRestoName(String rName) {
		findByClientEmail.setRestoName(rName);
		clientRepository.saveAndFlush(findByClientEmail);
		return true;
	}
	
	/* Add Menu */
	public boolean addMenuData(MenuEntity data,MultipartFile imageFile) throws IOException {
		
		String imageName = imageFile.getOriginalFilename().trim();
		System.out.println(imageName);
		inputStreamImage = imageFile.getInputStream();
		
		try {
			fileOutputStreamImage = new FileOutputStream(
					"D:\\OnlineFoodChat\\src\\main\\webapp\\Image\\"
							+ imageName);
		
			int bytesImage=0;
			while ((bytesImage = inputStreamImage.read()) != -1) {

				fileOutputStreamImage.write(bytesImage);
			}
		} finally {
			fileOutputStreamImage.close();
		
		}
		//data.setMenuImage(imageName);
		menuRepository.save(data);
		return true;
	}
	
}
