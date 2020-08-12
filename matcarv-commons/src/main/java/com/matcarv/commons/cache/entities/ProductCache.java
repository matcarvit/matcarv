/**
 * 
 */
package com.matcarv.commons.cache.entities;


import java.math.BigDecimal;

import org.springframework.data.redis.core.RedisHash;

import com.matcarv.commons.base.BaseEntity;
import com.matcarv.commons.enums.OrderStatusType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Data
@RedisHash("ProductCache")
@EqualsAndHashCode(callSuper = false, of = "id")
public class ProductCache extends BaseEntity<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4589019444750992426L;
	
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
	private BigDecimal quantity = new BigDecimal(0);
	
	/**
	 * 
	 */
	private OrderStatusType orderStatusType = OrderStatusType.CREATED;
}
