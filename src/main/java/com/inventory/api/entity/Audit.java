package com.inventory.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "audit")
public class Audit implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="entry_id")
    private int entryId;
	@Column(name="action")
	private String action;
	@Column(name="user_id")
	private int userId;
	@Column(name="warehouse_id")
	private int warehouseID;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="date_modified")
	private Date dateModified;
	@Column(name="value")
	private int value ;
	@Column(name="item_id")
	private int itemId;
	@Column(name="in_stock_after_change")
	private int inStock;
	
	
	@Column(name="item_Name")
	private String itemName;
	
	@Column(name="user_Name")
	private String userName;
	
	@Column(name="warehouse_name")
	private String warehouseName;
	
	@Column(name="item_subtype")
	private String itemSubtype;
	
	@Transient
	private Date fromDate;
	
	@Transient
	private Date toDate;
	
	public Audit(Date modifiedDate,String action,int value,String warehouseName,String itemName,String userName,int valueAfterChange){
		this.dateModified= modifiedDate; 
		 this.action= action ;
		 this.value= value ;
		 this.warehouseName= warehouseName ;
		 this.itemName= itemName; 
		 this.userName= userName;
		 this.inStock=valueAfterChange;
	}
	 
}
