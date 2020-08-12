/**
 * 
 */
package com.matcarv.order.repository;

import java.util.List;

import com.matcarv.commons.base.BaseRepository;
import com.matcarv.order.entities.Order;
import com.matcarv.order.entities.OrderItem;

/**
 * @author weslleymatosdecarvalho
 *
 */
public interface OrderItemRepository extends BaseRepository<OrderItem, String>{

	/**
	 * 
	 * @param order
	 * @return
	 */
	public List<OrderItem> findByOrder(final Order order);
	
	/**
	 * 
	 * @param order
	 */
	public void deleteByOrder(final Order order);
	
}
