package com.inventory.api.service;


import com.inventory.api.entity.Audit;
import com.inventory.api.entity.Item;
import com.inventory.api.entity.User;
import com.inventory.api.repository.AuditRepository;
import com.inventory.api.repository.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

	 @Autowired
	 private AuditRepository repository; 
	
	public List<Audit> getAllListAudit() {
		List<Audit> auditList = new ArrayList<Audit>();
		List<Object[]> listObject= repository.findListAudit();
		for(Object[] obj : listObject){
			   Date modifiedDate = (Date) obj[0];
			   String action = (String) obj[1];
			   int value =(int) obj[2];
			   String warehouseName = (String) obj[3];
			   String itemName = (String) obj[4];
			   String userName= (String)obj[5];
			   int valueAfterChange= (int)obj[6];
			   auditList.add(new Audit(modifiedDate,action,value,warehouseName,itemName,userName,valueAfterChange));
			}
		
		return auditList;
	}

	public List<Audit> getAuditSerachListData(Audit audit) {
		List<Audit> auditList = new ArrayList<Audit>();
		List<Object[]> listObject=null;
//		if(audit.getAction().equalsIgnoreCase("All")) {
//			listObject= repository.findSearchListAuditData(audit.getFromDate(),audit.getToDate());
//		}else {
//			 listObject= repository.findSearchListAuditData(audit.getFromDate(),audit.getToDate(),audit.getAction());
//		}
		listObject= repository.findSearchListAuditData(audit.getFromDate(),audit.getToDate());
		for(Object[] obj : listObject){
			   Date modifiedDate = (Date) obj[0];
			   String action = (String) obj[1];
			   int value =(int) obj[2];
			   String warehouseName = (String) obj[3];
			   String itemName = (String) obj[4];
			   String userName= (String)obj[5];
			   int valueAfterChange= (int)obj[6];
			   auditList.add(new Audit(modifiedDate,action,value,warehouseName,itemName,userName,valueAfterChange));
			}
		
		return auditList;
	}

	public List<Audit> getFindAuditbyItemName(int id) {
		List<Audit> auditList = new ArrayList<Audit>();
		List<Object[]> listObject= repository.findListAuditbyItemName(id);
		for(Object[] obj : listObject){
			   Date modifiedDate = (Date) obj[0];
			   String action = (String) obj[1];
			   int value =(int) obj[2];
			   String warehouseName = (String) obj[3];
			   String itemName = (String) obj[4];
			   String userName= (String)obj[5];
			   int valueAfterChange= (int)obj[6];
			   auditList.add(new Audit(modifiedDate,action,value,warehouseName,itemName,userName,valueAfterChange));
			}
		
		return auditList;
	}

}
