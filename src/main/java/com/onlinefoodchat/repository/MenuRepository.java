package com.onlinefoodchat.repository;

import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlinefoodchat.entity.MenuEntity;

@Repository
public interface MenuRepository extends JpaRepository<MenuEntity, Integer> {
	public List<MenuEntity> findByClientemail(String clientEmail);

	public MenuEntity findByMenuId(int id);
}

