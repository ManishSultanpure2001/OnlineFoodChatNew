package com.onlinefoodchat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.onlinefoodchat.entity.AddCart;
import com.onlinefoodchat.entity.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
	@Modifying
	@Query(value="update notification set status=:status,user_message=:message where (identify_id=:id and orderid=:orderId)",nativeQuery = true)
	public int updateUserNotification(@Param("status") String status,@Param("message") String message,@Param("id") String id,@Param("orderId") String orderId);
	@Modifying
	@Query(value="update notification set status=:status,clint_message=:message where (identify_id=:id and orderid=:orderId)",nativeQuery = true)
	public int updateClientNotification(@Param("status") String status,@Param("message") String message,@Param("id") String id,@Param("orderId") String orderId);
	@Modifying
	@Query(value="update notification set user_delete_status=:deleteStatus where (orderid=:orderId)",nativeQuery = true)
	public int updateUserDeleteNotification(@Param("deleteStatus") int status,@Param("orderId") int orderId);
	@Modifying
	@Query(value="update notification set client_delete_status=:deleteStatus where (orderid=:orderId)",nativeQuery = true)
	public int updateClientDeleteNotification(@Param("deleteStatus") int status,@Param("orderId") int orderId);

	public List<Notification> findByIdentifyId(int id);
	public List<Notification> findByIdentifyIdOrRestoName(int id,String restoName);
}
