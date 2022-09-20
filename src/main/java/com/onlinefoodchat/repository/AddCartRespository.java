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
	
	public List<AddCart> findByUserEmail(String userEmail);
	@Modifying
	@Query(value="delete from add_cart where user_email=:email",nativeQuery = true)
	public int deleteDish(@Param("email") String email);
	
	@Modifying
	@Query(value="update add_cart set menu_quantity=:quantity,totle_price=:totalPrice,sum_of_totle_price=:sumTotal where cart_id=:id",nativeQuery = true)
	public int updateData(@Param("quantity") String quantity,@Param("totalPrice") String totalPrice,@Param("sumTotal") String sumTotal,@Param("id") String id);

	@Modifying
	@Query(value="SELECT IFNULL(SUM(totle_price),0) FROM AddCart",nativeQuery = true)
	public int sumOfOrder();

	
}
