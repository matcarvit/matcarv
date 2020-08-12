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
import com.matcarv.commons.cache.business.ProductCacheBusiness;
import com.matcarv.commons.cache.entities.ProductCache;
import com.matcarv.commons.enums.OrderStatusType;
import com.matcarv.commons.enums.TransactionType;
import com.matcarv.order.entities.Order;
import com.matcarv.order.entities.OrderItem;
import com.matcarv.order.repository.OrderItemRepository;

import lombok.Getter;
import net.bytebuddy.utility.RandomString;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Component
@Transactional(propagation = Propagation.SUPPORTS)
public class OrderItemBusinessImpl extends AbstractBaseBusinessImpl<OrderItem, String> implements OrderItemBusiness {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1534821946224992540L;
	
	@Getter
	@Autowired
	private OrderItemRepository repository;
	
	/**
	 * 
	 */
	@Getter
	@Autowired
	private ProductCacheBusiness productCacheBusiness;
	
	/**
	 * 
	 */
	@Override
	protected OrderItem insertOrUpdate(final OrderItem entity, final TransactionType type) {
		final OrderItem merged = super.insertOrUpdate(entity, type);
		final RandomString random = new RandomString();
		final ProductCache cache = new ProductCache();
		cache.setId(random.nextString());
		cache.setProductId(entity.getProductId());
		cache.setQuantity(entity.getQuantity());
		
		getProductCacheBusiness().processInsert(cache);
		
		return merged;
	}

	/**
	 * 
	 */
	@Override
	public List<OrderItem> findByOrder(final Order order) {
		final List<OrderItem> list = getRepository().findByOrder(order);
		
		return list;
	}
	
	/**
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteByOrder(final Order order) {
		getRepository().deleteByOrder(order);
	}

	/**
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void cancelByOrder(final Order order) {
		final List<OrderItem> list = findByOrder(order);
		for(final OrderItem entity : list) {
			entity.setOrderStatusType(OrderStatusType.CANCELLED);
			
			insertOrUpdate(entity, TransactionType.CANCEL);
			
			final RandomString random = new RandomString();
			final ProductCache cache = new ProductCache();
			cache.setId(random.nextString());
			cache.setProductId(entity.getProductId());
			cache.setQuantity(entity.getQuantity());
			cache.setOrderStatusType(OrderStatusType.CANCELLED);
			
			getProductCacheBusiness().processInsert(cache);
		}
	}
	
	
}
