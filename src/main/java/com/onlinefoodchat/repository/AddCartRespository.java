package com.onlinefoodchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlinefoodchat.entity.AddCart;

@Repository
public interface AddCartRespository extends JpaRepository<AddCart, Integer> {
	public List<AddCart> findByRestoNameAndUserEmail(String restoName,String userEmail);
	
	public List<AddCart> findByUserEmail(String restoName);
	@Modifying
	@Query(value="delete from add_cart where user_email=:email",nativeQuery = true)
	public int deleteDish(@Param("email") String email);
}
