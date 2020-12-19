/**
 * 
 */
package com.matcarv.order.business;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.matcarv.commons.base.AbstractBaseBusinessImpl;
import com.matcarv.commons.cache.business.ProductCacheBusiness;
import com.matcarv.commons.cache.entities.ProductCache;
import com.matcarv.commons.enums.OrderStatusType;
import com.matcarv.commons.enums.TransactionType;
import com.matcarv.commons.exceptions.BusinessException;
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
		
		switch (type) {
			case INSERT:
				final RandomString random = new RandomString();
				final ProductCache cache = new ProductCache();
				cache.setId(random.nextString());
				cache.setProductId(entity.getProductId());
				cache.setQuantity(entity.getQuantity());
				
				getProductCacheBusiness().processInsert(cache);
				break;
	
			default:
				break;
		}
		
		
		return merged;
	}
	
	/**
	 * 
	 */
	@Override
	protected void processValidate(final OrderItem entity, final TransactionType transactionType) {
		if(entity.getOrder() == null) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, getMessage("msg.campo.obrigatorio", getMessage("lbl.pedido")));
		}
		
		if(entity.getOrderStatusType() == null) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, getMessage("msg.campo.obrigatorio", getMessage("lbl.status")));
		}
		
		if(StringUtils.isEmpty(entity.getProductId())) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, getMessage("msg.campo.obrigatorio", getMessage("lbl.id.produto")));
		}
		
		if(StringUtils.isEmpty(entity.getProductDesciption())) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, getMessage("msg.campo.obrigatorio", getMessage("lbl.descricao.produto")));
		}
		
		if(entity.getQuantity() == null) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, getMessage("msg.campo.obrigatorio", getMessage("lbl.quantidade")));
		}
		
		if(entity.getQuantity().equals(new BigDecimal(0))) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, getMessage("msg.quantidade.maior.zero"));
		}
		
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
