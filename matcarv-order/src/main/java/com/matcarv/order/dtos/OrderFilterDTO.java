/**
 * 
 */
package com.matcarv.order.dtos;

import com.matcarv.commons.base.BaseFilterDTO;
import com.matcarv.commons.enums.OrderStatusType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author weslleymatosdecarvalho
 *
 */

public class OrderFilterDTO extends BaseFilterDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4673390709496170245L;
	
	/**
	 * 
	 */
	@Getter
	@Setter
	private String productDesciption;
	
	/**
	 * 
	 */
	@Getter
	@Setter
	private OrderStatusType orderStatusType;

}
