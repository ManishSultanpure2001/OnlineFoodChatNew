package com.onlinefoodchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onlinefoodchat.entity.MyOrders;
import com.onlinefoodchat.entity.OrderProductList;

public interface OrderProductListRepository extends JpaRepository<OrderProductList, Integer> {
//
//	public List<OrderProductList> findByOrderId(int id);
 	@Modifying
 	@Query(value="Select * from order_product_list where order_id=:id",nativeQuery = true)
 	public List<OrderProductList> menuList(@Param("id") int id);
	
 	public int  deleteByMyOrders(MyOrders myOrders);
 	@Modifying
	@Query(value="update my_orders set order_status=:status where order_id=:id",nativeQuery = true)
	public int updateStatus(@Param("status") String status,@Param("id") String id);
//	
//	@EntityGraph(value = "OrderProductList.orderId", type = EntityGraphType.FETCH)
//	public OrderProductList findByMyOrders(int id);
}
