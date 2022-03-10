package com.inventory.api.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item")
public class Item implements Serializable, Cloneable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private int itemId;
	@Column(name="item_name")
	private String itemName;
	@Column(name="item_subtype")
	private String itemSubtype;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name="date_added")
	private Date dateAdded;
	@Column(name="in_stock")
	private int inStock;
	@Column(name="type")
	private String type;
	@Column(name="warehouse_id")
	private int warehouseID;
	@Transient
	@JsonProperty
	private String action;
	@Transient
	@JsonProperty
	private int userId;
	@Transient
	@JsonProperty
	private int value;
}
