package com.inventory.api.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.inventory.api.entity.Audit;
import com.inventory.api.entity.Warehouse;


public interface WarehouseRepository extends JpaRepository<Warehouse,Integer> {
	
	
	
	
}

