/**
 * 
 */
package com.matcarv.order.dtos;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Data
@EqualsAndHashCode(callSuper = false, of = "id")
public class OrderItemFormDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 160969671421375001L;

	/**
	 * 
	 */
	private String id;
	
	/**
	 * 
	 */
	private String productId;
	
	/**
	 * 
	 */
	private String productDesciption;
	
	/**
	 * 
	 */
	private BigDecimal quantity;
}
