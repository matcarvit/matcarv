/**
 * 
 */
package com.matcarv.order.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.matcarv.commons.enums.OrderStatusType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Data
@EqualsAndHashCode(callSuper = false, of = "id")
public class OrderFormDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1730853827679089429L;

	/**
	 * 
	 */
	private String id;
	
	/**
	 * 
	 */
	private Date orderDate;
	
	/**
	 * 
	 */
	private OrderStatusType orderStatusType;
	
	/**
	 * 
	 */
	private List<OrderItemFormDTO> items = new ArrayList<>();
}
