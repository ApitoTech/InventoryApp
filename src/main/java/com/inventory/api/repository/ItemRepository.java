package com.inventory.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.inventory.api.entity.Item;
import com.inventory.api.entity.Warehouse;


public interface ItemRepository extends JpaRepository<Item,Integer> {

	
	@Query(value = "SELECT a from Item a")
	Item getAllListItem();

	@Query("SELECT m FROM Item m WHERE m.itemName LIKE %?1%")
	List<Item> searchByItem(String title);
	
	@Query("SELECT m FROM Item m WHERE m.warehouseID = ?1")
	List<Item> findAllById(int id);

	@Query("SELECT m FROM Item m WHERE m.itemName LIKE ?1%")
	List<Item> searchByItemFirstChar(String name);

	@Query("SELECT m FROM Item m WHERE m.itemName LIKE ?1% and m.warehouseID =?2")
	List<Item> searchByItemList(String itemName, int warehouseID);
	
	@Transactional
	@Modifying
	@Query("Delete FROM Item m WHERE m.itemId = ?1")
	void deleteItemById(int id);

	@Query("SELECT w FROM Warehouse w")
	List<Warehouse> getWarehouseData();

	//@Query("SELECT m FROM Item m WHERE m.itemName LIKE ?1% and m.warehouseID =?2")
	//Item searchByItem(String itemName, int warehouseID);
	
	@Modifying
	@Query("update Item m set m.inStock = ?2 where m.itemId = ?1 and m.warehouseID =?3")
	void updateDiffWarehouseData(int itemId, int value, int warehouseID);

	@Modifying
	@Query("update Item m set m.inStock = ?1 where m.itemId = ?2")
	void updateExistingWarehouseData(int inStock, int itemId);

	@Query("SELECT m FROM Item m WHERE m.itemName = ?1 and m.itemSubtype = ?2 and m.warehouseID =?3")
	Item searchByItem(String itemName, String itemSubtype, int warehouseID);

	@Query("SELECT m FROM Item m WHERE m.itemName = ?1 and m.itemSubtype = ?2 and m.type = ?3 and m.warehouseID =?4")
	Item searchByItemBeforeAdd(String itemName, String itemSubtype, String type, int warehouseID);
	
	@Modifying
	@Query("update Item m set m.itemName = ?1,m.itemSubtype = ?2 where m.itemId = ?3")
	Item updateName(String itemName, String itemSubtype, int itemId);

}

