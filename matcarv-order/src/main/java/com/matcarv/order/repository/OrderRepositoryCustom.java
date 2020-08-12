/**
 * 
 */
package com.matcarv.order.repository;

import java.io.Serializable;
import java.util.List;

import com.matcarv.order.dtos.OrderFilterDTO;
import com.matcarv.order.dtos.OrderSearchDTO;

/**
 * @author weslleymatosdecarvalho
 *
 */
public interface OrderRepositoryCustom extends Serializable {

	/**
	 * 
	 * @param filter
	 * @return
	 */
	public List<OrderSearchDTO> findByFilter(final OrderFilterDTO filter);
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	public Long getCount(final OrderFilterDTO filter);
}
