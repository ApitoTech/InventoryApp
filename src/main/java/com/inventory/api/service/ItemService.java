package com.inventory.api.service;

import com.inventory.api.entity.Audit;
import com.inventory.api.entity.ExcelData;
import com.inventory.api.entity.Item;
import com.inventory.api.entity.Warehouse;
import com.inventory.api.repository.AuditRepository;
import com.inventory.api.repository.ItemRepository;
import com.inventory.api.repository.WarehouseRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;
    
    @Autowired
    private AuditRepository auditRepository;
    
    @Autowired
    private WarehouseRepository warehouseRepository;

    public String saveItem(Item item) {
    	String msg="";
    	Item diffWarehousedata=repository.searchByItemBeforeAdd(item.getItemName(),item.getItemSubtype(),item.getType(),item.getWarehouseID());
    	if(diffWarehousedata != null) {
    		 msg="Item Already Exists" ;
    	}
    	else {
    		
    		Audit audit =new Audit();
    		audit.setDateModified(new Date());
    		audit.setAction("Add Item");
    		//audit.setUserId(item.getUserId());
    		audit.setWarehouseID(item.getWarehouseID());
    		audit.setValue(item.getInStock());
    		audit.setItemId(item.getItemId());
    		audit.setInStock(item.getInStock());
    		audit.setUserId(1);//remove once other user adds
    		audit.setItemName(item.getItemName());
    		audit.setItemSubtype(item.getItemSubtype());
    		auditRepository.save(audit);
    		
    		item.setDateAdded(new Date());
    		item.setUserId(1);//remove once other user adds
            repository.save(item);
            msg="One Item Added Successfully";
    	}
		return msg;
    }

	public List<ExcelData> getFindAll(int id) {
		List<ExcelData> excelListData=new ArrayList<ExcelData>();
		List<Item> existingData= repository.findAllById(id);
		for(Item v : existingData) {
			ExcelData excelData=new ExcelData();
			excelData.setItemId(v.getItemId());
			excelData.setItemName(v.getItemName());
			excelData.setInStock(v.getInStock());
			excelData.setItemSubtype(v.getItemSubtype());
			excelData.setDateAdded(v.getDateAdded());
			excelData.setType(v.getType());
			excelData.setWarehouseID(v.getWarehouseID());
			excelListData.add(excelData);
		}
		return excelListData;
	}

	public Item updateItem(Item item) {
		Audit audit =new Audit();
		audit.setDateModified(new Date());
		audit.setAction(item.getAction());
		//audit.setUserId(item.getUserId());
		audit.setWarehouseID(item.getWarehouseID());
		audit.setValue(item.getValue());
		audit.setItemId(item.getItemId());
		audit.setInStock(item.getInStock());
		audit.setUserId(1);//remove once other user adds
		audit.setItemName(item.getItemName());
		audit.setItemSubtype(item.getItemSubtype());
		auditRepository.save(audit);
		
		if(item.getInStock()!=0) {
			Item existingItem = repository.findById(item.getItemId()).orElse(null);
			existingItem.setItemName(item.getItemName());
			existingItem.setInStock(item.getInStock());
			existingItem.setDateAdded(new Date());
			//audit.setUserId(1);//remove once other user adds
			return repository.save(existingItem);
		}else {
			repository.deleteItemById(item.getItemId());
		}
		
		return item;
	}

	public List<Item> deleteItem(int id) {
		Item existingItem = repository.findById(id).orElse(null);
		
		if(existingItem != null) {
			Audit audit =new Audit();
			audit.setDateModified(new Date());
			audit.setAction("Delete");
			audit.setWarehouseID(existingItem.getWarehouseID());
			audit.setValue(existingItem.getValue());
			audit.setItemId(existingItem.getItemId());
			audit.setInStock(existingItem.getInStock());
			audit.setUserId(1);//remove once other user adds
			audit.setItemName(existingItem.getItemName());
			audit.setItemSubtype(existingItem.getItemSubtype());
			auditRepository.save(audit);
			
			repository.deleteItemById(id);
		}
		
		return repository.findAllById(existingItem.getWarehouseID());
	}

	public List<Item> getItemByName(String name) {
		 return repository.searchByItemFirstChar(name);
	}

	public List<Item> getItemByName(Item item) {
		return repository.searchByItemList(item.getItemName(),item.getWarehouseID());
	}

	public List<Item> getFindAllByChar(Item item) {
		return repository.searchByItemList(item.getItemName(),item.getWarehouseID());
	}

	public List<ExcelData> getItemitemExcelData(Item item) {
		
		List<ExcelData> excelListData=new ArrayList<ExcelData>();
		List<Item> existingData= repository.searchByItemList(item.getItemName(),item.getWarehouseID());
		for(Item v : existingData) {
			ExcelData excelData=new ExcelData();
			excelData.setItemName(v.getItemName());
			excelData.setInStock(v.getInStock());
			excelData.setItemSubtype(v.getItemSubtype());
			excelData.setDateAdded(v.getDateAdded());
			//excelData.setType(v.getType());
			excelData.setWarehouseID(v.getWarehouseID());
			excelListData.add(excelData);
		}
		return excelListData;
	}

	public List<Warehouse> getWarehouseData() {
		return repository.getWarehouseData();
	}

	public Warehouse saveWarehouse(Warehouse warehouse) {
		 return warehouseRepository.save(warehouse);
	}

	public void deleteWarehouse(int id) {
		warehouseRepository.deleteById(id);
	}

	public Warehouse updateWarehouse(Warehouse warehouse) {
		return warehouseRepository.save(warehouse);
	}

	@SuppressWarnings({ "null", "unused" })
	@Transactional
	public Item transferItem(Item item) throws Exception {
		Item existingItem = repository.findById(item.getItemId()).orElse(null);
		Audit audit1 =new Audit();
		audit1.setDateModified(new Date());
		audit1.setAction("Transferred From");
		audit1.setWarehouseID(existingItem.getWarehouseID());
		audit1.setValue(existingItem.getInStock());
		audit1.setItemId(existingItem.getItemId());
		audit1.setInStock(existingItem.getInStock()-item.getValue());
		audit1.setUserId(1);//remove once other user adds
		audit1.setItemName(existingItem.getItemName());
		audit1.setItemSubtype(existingItem.getItemSubtype());
		auditRepository.save(audit1);
		
		//transfer to different warehouse
		Item diffWarehousedata= repository.searchByItem(item.getItemName(),item.getItemSubtype(),item.getWarehouseID());
		//should not be same warehouse 
			//item already exist
			if(diffWarehousedata != null) {
				Audit audit =new Audit();
				audit.setDateModified(new Date());
				audit.setAction("Transferred To");
				audit.setWarehouseID(item.getWarehouseID());
				audit.setValue(diffWarehousedata.getInStock());
				audit.setItemId(existingItem.getItemId());
				audit.setInStock(item.getValue()+diffWarehousedata.getInStock());
				audit.setUserId(1);//remove once other user adds
				audit.setItemName(existingItem.getItemName());
				audit.setItemSubtype(existingItem.getItemSubtype());
				auditRepository.save(audit);
				
				int total_value=item.getValue()+diffWarehousedata.getInStock();
				repository.updateDiffWarehouseData(diffWarehousedata.getItemId(),total_value,item.getWarehouseID());
			}else {
				Audit audit =new Audit();
				audit.setDateModified(new Date());
				audit.setAction("Transferred To");
				audit.setWarehouseID(item.getWarehouseID());
				audit.setValue(0);
				audit.setItemId(item.getItemId());
				audit.setInStock(item.getValue());
				audit.setUserId(1);//remove once other user adds
				audit.setItemName(item.getItemName());
				audit.setItemSubtype(item.getItemSubtype());
				auditRepository.save(audit);
				
				Item newItem =new Item();
				newItem.setDateAdded(new Date());
				newItem.setInStock(item.getValue());
				newItem.setItemName(item.getItemName());
				newItem.setItemSubtype(item.getItemSubtype());
				newItem.setType(item.getType());
				newItem.setWarehouseID(item.getWarehouseID());
				repository.save(newItem);
			}
		if(existingItem.getInStock()-item.getValue()==0) {
			//delete if existing item is zero 
			repository.deleteItemById(existingItem.getItemId());
		}else {
			//update existing warehouse data
			repository.updateExistingWarehouseData(item.getInStock(),item.getItemId());
		}
		return item;
	}

	public Item updateName(Item item) {
		//return repository.updateName(item.getItemName(),item.getItemSubtype(),item.getItemId());
		Audit audit =new Audit();
		audit.setDateModified(new Date());
		audit.setAction(item.getAction());
		//audit.setUserId(item.getUserId());
		audit.setWarehouseID(item.getWarehouseID());
		audit.setValue(item.getValue());
		audit.setItemId(item.getItemId());
		audit.setInStock(item.getInStock());
		audit.setUserId(1);//remove once other user adds
		audit.setItemName(item.getItemName());
		audit.setItemSubtype(item.getItemSubtype());
		auditRepository.save(audit);
		
		Item existingItem = repository.findById(item.getItemId()).orElse(null);
		existingItem.setItemName(item.getItemName());
		existingItem.setItemSubtype(item.getItemSubtype());
		existingItem.setDateAdded(new Date());
		//audit.setUserId(1);//remove once other user adds
		return repository.save(existingItem);
	}


}
