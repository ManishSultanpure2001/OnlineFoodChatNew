package com.onlinefoodchat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onlinefoodchat.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
	@Modifying
	@Query(value="update notification set status=:status,user_message=:message where (identify_id=:id and orderid=:orderId)",nativeQuery = true)
	public int updateUserNotification(@Param("status") String status,@Param("message") String message,@Param("id") String id,@Param("orderId") String orderId);

	//	@Modifying
//	@Query(value="update notification set status=:status,message=:message where (orderid=:orderId)",nativeQuery = true)
//	public int updateUserNotification(@Param("status") String status,@Param("message") String message,@Param("orderId") String orderId);

}
