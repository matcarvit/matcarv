/**
 * 
 */
package com.matcarv.order.business;

import java.util.List;

import com.matcarv.commons.base.BaseBusiness;
import com.matcarv.order.entities.Order;
import com.matcarv.order.entities.OrderItem;

/**
 * @author weslleymatosdecarvalho
 *
 */
public interface OrderItemBusiness extends BaseBusiness<OrderItem, String>{

	/**
	 * 
	 * @param order
	 * @return
	 */
	public List<OrderItem> findByOrder(final Order order);
	
	/**
	 * 
	 * @param order
	 * @return
	 */
	public void cancelByOrder(final Order order);
}
