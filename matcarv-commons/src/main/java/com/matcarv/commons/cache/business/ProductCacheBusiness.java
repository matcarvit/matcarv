/**
 * 
 */
package com.matcarv.commons.cache.business;

import java.io.Serializable;
import java.util.List;

import com.matcarv.commons.cache.entities.ProductCache;
import com.matcarv.commons.enums.OrderStatusType;

/**
 * @author weslleymatosdecarvalho
 *
 */
public interface ProductCacheBusiness extends Serializable {

	/**
	 * 
	 */
	public void processInsert(final ProductCache entity);
		
	/**
	 * 
	 * @param orderStatusType
	 * @return
	 */
	public List<ProductCache> findByOrderStatusType(final OrderStatusType orderStatusType);
	
	/**
	 * 
	 * @param id
	 */
	public void delete(final String id);
}
