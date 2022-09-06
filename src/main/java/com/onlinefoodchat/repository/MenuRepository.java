package com.onlinefoodchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinefoodchat.entity.MenuEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Integer>{
	
}
