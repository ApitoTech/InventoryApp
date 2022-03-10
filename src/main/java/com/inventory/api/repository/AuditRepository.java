package com.inventory.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inventory.api.entity.Audit;


public interface AuditRepository extends JpaRepository<Audit,Integer> {
	
	@Query("SELECT m.dateModified, m.action , m.value , m.warehouseID, m.itemName, u.userName , m.inStock FROM Audit m join  User u  ")
	List<Object[]> findListAudit();
	
	@Query("SELECT m.dateModified, m.action , m.value , w.warehouseName, m.itemName, u.userName , m.inStock FROM Audit m  join User u on u.userId=m.userId join Warehouse w on w.warehouseId=m.warehouseID where m.dateModified between ?1 and ?2  ")
	List<Object[]> findSearchListAuditData(Date fromDate, Date toDate);

	@Query("SELECT m.dateModified, m.action , m.value , m.warehouseID, m.itemName, u.userName , m.inStock FROM Audit m  join User u on u.userId=m.userId where (m.dateModified between ?1 and ?2) or m.action = ?3 ")
	List<Object[]> findSearchListAuditData(Date fromDate, Date toDate, String action);

	@Query("SELECT m.dateModified, m.action , m.value , w.warehouseName, m.itemName, u.userName , m.inStock FROM Audit m  join User u on u.userId=m.userId join Warehouse w on w.warehouseId=m.warehouseID where m.itemId = ?1 ")
	List<Object[]> findListAuditbyItemName(int id);
	
	//@Query("SELECT m.dateModified, m.action , m.value , m.warehouseID, i.itemName, u.userName FROM Audit m join Item i on m.itemId=i.itemId join User u on u.userId=m.userId where m.dateModified between ?1 and ?2")
	//List<Object[]> findSearchListAuditData(Audit audit);
	
//	@Query("SELECT m.dateModified, m.action , m.value , m.warehouseID, i.itemName, u.userName FROM Audit m join Item i on m.itemId=i.itemId join User u on u.userId=m.userId where (m.dateModified between ?1 and ?2) or m.action = ?3")
//	List<Object[]> findSearchListAuditData(Date fromDate, Date toDate, String action);
	
	
}

