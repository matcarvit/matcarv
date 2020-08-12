/**
 * 
 */
package com.matcarv.order.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.matcarv.commons.base.AbstractBaseBusinessImpl;
import com.matcarv.commons.enums.OrderStatusType;
import com.matcarv.commons.enums.TransactionType;
import com.matcarv.order.dtos.OrderFilterDTO;
import com.matcarv.order.dtos.OrderSearchDTO;
import com.matcarv.order.entities.Order;
import com.matcarv.order.entities.OrderItem;
import com.matcarv.order.repository.OrderRepository;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Component
@Transactional(propagation = Propagation.SUPPORTS)
public class OrderBusinessImpl extends AbstractBaseBusinessImpl<Order, String> implements OrderBusiness {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6353581476577614227L;
	
	/**
	 * 
	 */
	@Getter
	@Autowired
	private OrderRepository repository;
	
	/**
	 * 
	 */
	@Getter
	@Autowired
	private OrderItemBusiness orderItemBusiness;
	
	/**
	 * 
	 */
	@Override
	protected Order insertOrUpdate(final Order entity, final TransactionType type) {
		final Order merged = super.insertOrUpdate(entity, type);
		entity.setId(merged.getId());
		
		getOrderItemBusiness().deleteByOrder(merged);
		
		for(final OrderItem item : entity.getItems()) {
			item.setOrder(merged);
			getOrderItemBusiness().processInsert(item);
		}
		
		return merged;
	}
	
	/**
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Order cancel(final String id) {
		final Order entity = getRepository().getOne(id);
		entity.setOrderStatusType(OrderStatusType.CANCELLED);
		
		insertOrUpdate(entity, TransactionType.CANCEL);
		
		getOrderItemBusiness().cancelByOrder(entity);
		
		return entity;
	}
	
	/**
	 * 
	 */
	@Override
	public void deleteById(final String id) {
		final Order entity = getRepository().getOne(id);
		getOrderItemBusiness().deleteByOrder(entity);
		
		super.deleteById(id);
	}
	
	/**
	 * 
	 */
	@Override
	public Order find(final String id) {
		final Order entity = super.find(id);
		entity.setItems(getOrderItemBusiness().findByOrder(entity));
		
		return entity;
	}

	/**
	 * 
	 */
	@Override
	public List<OrderSearchDTO> findByFilter(final OrderFilterDTO filter) {
		return getRepository().findByFilter(filter);
	}

	/**
	 * 
	 */
	@Override
	public Long getCount(final OrderFilterDTO filter) {
		return getRepository().getCount(filter);
	}
}
