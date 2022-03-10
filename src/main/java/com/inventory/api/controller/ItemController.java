package com.inventory.api.controller;

import com.inventory.api.entity.ExcelData;
import com.inventory.api.entity.Item;
import com.inventory.api.entity.Warehouse;
import com.inventory.api.service.ItemService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class ItemController {
	 @Autowired
	 private ItemService service;

    @PostMapping("/addItem")
    public String addItem(@RequestBody Item item) {
    	System.out.println("ioeutoeitueroitueoitreuotireuoit,m,x");
        return service.saveItem(item);
    }
    
    @PostMapping("/addWarehouse")
    public Warehouse addWarehouse(@RequestBody Warehouse warehouse) {
        return service.saveWarehouse(warehouse);
    }
    
    @PostMapping("/modifyItem")
    public Item modifyItem(@RequestBody Item item) {
    	 return service.updateItem(item);
    }
    
    @PostMapping("/modifyName")
    public Item modifyName(@RequestBody Item item) {
    	 return service.updateName(item);
    }
    
    @PostMapping("/tansferItem")
    public Item transferItem(@RequestBody Item item) throws Exception {
    	 return service.transferItem(item);
    	//return null;
    }
    
    @PostMapping("/modifyWarehouse")
    public Warehouse modifyWarehouse(@RequestBody Warehouse warehouse) {
    	 return service.updateWarehouse(warehouse);
    }
    
    @GetMapping("/getAllListItem/{id}")
    public List<ExcelData> getAllListItem(@PathVariable int id) {
        return service.getFindAll(id);
    }
    
    @PostMapping("/getAllListItemChar")
    public List<Item> getAllListItemChar(@RequestBody Item item) {
        return service.getFindAllByChar(item);
    }
   
    @GetMapping("/deleteItem/{id}")
    public List<Item> deleteItem(@PathVariable int id) {
        return service.deleteItem(id);
    }
    
    @GetMapping("/deleteWarehouse/{id}")
    public void deleteWarehouse(@PathVariable int id) {
         service.deleteWarehouse(id);
    }
    
    @GetMapping("/item/{alpha}")
    public List<Item> findItemByName(@PathVariable String alpha) {
        return service.getItemByName(alpha);
    }
    
    @PostMapping("/item")
    public List<Item> findItemByName(@RequestBody Item item) {
        return service.getItemByName(item);
    }
    
    @PostMapping("/itemExcelData")
    public List<ExcelData> findItemforExcel(@RequestBody Item item) {
        return service.getItemitemExcelData(item);
    }

    @GetMapping("/warehouse")
    public List<Warehouse> fetchWarehouseData(){
    	System.out.println("called wareshouse api-------------------------");
		return service.getWarehouseData();
    }
}
