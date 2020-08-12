/**
 * 
 */
package com.matcarv.order.dtos;

import java.io.Serializable;
import java.util.Date;

import com.matcarv.commons.enums.OrderStatusType;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Getter
public class OrderSearchDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 965063895147741577L;
	
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
	 * @param id
	 * @param orderDate
	 * @param orderStatusType
	 */
	public OrderSearchDTO(final String id, final Date orderDate, final OrderStatusType orderStatusType) {
		super();
		this.id = id;
		this.orderDate = orderDate;
		this.orderStatusType = orderStatusType;
	}

	
}
