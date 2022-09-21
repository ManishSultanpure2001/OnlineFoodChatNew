package com.onlinefoodchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onlinefoodchat.entity.AddCart;
import com.onlinefoodchat.entity.MyOrders;

public interface MyOrdersRepository extends JpaRepository<MyOrders, Integer> {
	public List<MyOrders> findByRestoName(String restoName);
	public List<MyOrders> findByUserEmail(String email);
	
//	@Modifying
//	@Query(value="update my_orders set order_status=:status where order_id=:id",nativeQuery = true)
//	public int updateStatus(@Param("status") String status,@Param("id") String id);
}
