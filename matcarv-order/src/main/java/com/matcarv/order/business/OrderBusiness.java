/**
 * 
 */
package com.matcarv.order.business;

import java.util.List;

import com.matcarv.commons.base.BaseBusiness;
import com.matcarv.order.dtos.OrderFilterDTO;
import com.matcarv.order.dtos.OrderSearchDTO;
import com.matcarv.order.entities.Order;

/**
 * @author weslleymatosdecarvalho
 *
 */
public interface OrderBusiness extends BaseBusiness<Order, String> {

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
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Order cancel(final String id);
}
