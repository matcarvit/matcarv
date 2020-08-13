/**
 * 
 */
package com.matcarv.products.business;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.matcarv.commons.base.AbstractBaseBusinessImpl;
import com.matcarv.commons.cache.business.ProductCacheBusiness;
import com.matcarv.commons.cache.entities.ProductCache;
import com.matcarv.commons.enums.OrderStatusType;
import com.matcarv.commons.enums.TransactionType;
import com.matcarv.products.dtos.ProductFilterDTO;
import com.matcarv.products.dtos.ProductSearchDTO;
import com.matcarv.products.entities.Product;
import com.matcarv.products.repository.ProductRepository;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Component
@Transactional(propagation = Propagation.SUPPORTS)
public class ProductBusinessImpl extends AbstractBaseBusinessImpl<Product, String> implements ProductBusiness {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7566441852731941496L;
	
	/**
	 * 
	 */
	private static final Log log = LogFactory.getLog(ProductBusinessImpl.class);
	
	/**
	 * 
	 */
	@Getter
	@Autowired
	private ProductRepository repository;
	
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
	@Transactional(propagation = Propagation.REQUIRED)
	public void processCreatedProductFromCache() {
		final List<ProductCache> list = getProductCacheBusiness().findByOrderStatusType(OrderStatusType.CREATED);
		for(final ProductCache cache : list) {
			try {
				final Product entity = super.find(cache.getProductId());
				entity.setQuantity(entity.getQuantity().subtract(cache.getQuantity()));
			
				insertOrUpdate(entity, TransactionType.UPDATE);
				
				log.info("processCreatedProductFromCache: " + cache);
			} catch(EntityNotFoundException e) {
				log.error(e);
			} finally {
				getProductCacheBusiness().delete(cache.getId());
			}

		}
	}
	
	/**
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void processCancelledProductFromCache() {
		final List<ProductCache> list = getProductCacheBusiness().findByOrderStatusType(OrderStatusType.CANCELLED);
		for(final ProductCache cache : list) {
			try {
				final Product entity = super.find(cache.getProductId());
				entity.setQuantity(entity.getQuantity().add(cache.getQuantity()));
			
				insertOrUpdate(entity, TransactionType.UPDATE);
				
				log.info("processCancelledProductFromCache: " + cache);
			} catch(EntityNotFoundException e) {
				log.error(e);
			} finally {
				getProductCacheBusiness().delete(cache.getId());
			}
		
		}
	}
	
	/**
	 * 
	 */
	@Override
	public List<ProductSearchDTO> findByFilter(final ProductFilterDTO filter) {
		return getRepository().findByFilter(filter);
	}

	/**
	 * 
	 */
	@Override
	public Long getCount(final ProductFilterDTO filter) {
		return getRepository().getCount(filter);
	}
}
