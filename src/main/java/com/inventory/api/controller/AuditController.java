package com.inventory.api.controller;

import com.inventory.api.entity.Audit;
import com.inventory.api.entity.Item;
import com.inventory.api.entity.User;
import com.inventory.api.service.AuditService;
import com.inventory.api.service.ItemService;
import com.inventory.api.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@CrossOrigin(origins = "*")
public class AuditController {
	 @Autowired
	 private AuditService auditService;
    
	 @GetMapping("/getAllListAudit")
	    public List<Audit> getAllListAudit() {
	        return auditService.getAllListAudit();
	    }
	 
	 @PostMapping("/getAuditSerachListData")
	    public List<Audit> getAuditSerachListData(@RequestBody Audit audit) {
	        return auditService.getAuditSerachListData(audit);
	    }
	 
		
		@GetMapping("/getSingleAuditItemList/{id}")
		public List<Audit> getSingleAuditItemList(@PathVariable int id) {
			return auditService.getFindAuditbyItemName(id);
		}
		 

}
