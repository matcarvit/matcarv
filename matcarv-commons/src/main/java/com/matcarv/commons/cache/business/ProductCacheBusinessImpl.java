/**
 * 
 */
package com.matcarv.commons.cache.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.matcarv.commons.cache.entities.ProductCache;
import com.matcarv.commons.cache.repository.ProductCacheRepository;
import com.matcarv.commons.enums.OrderStatusType;
import com.matcarv.commons.exceptions.BusinessException;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Component
public class ProductCacheBusinessImpl implements ProductCacheBusiness {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5943573009600139172L;
	
	/**
	 * 
	 */
	private static final Log log = LogFactory.getLog(ProductCacheBusinessImpl.class);
	
	/**
	 * 
	 */
	@Getter
	@Autowired
	private ProductCacheRepository repository;
	
	/**
	 * 
	 */
	@Getter
	@Autowired
	private MessageSource messageSource;

	/**
	 * 
	 */
	@Override
	public ProductCache processInsert(final ProductCache entity) {
		final Iterator<ProductCache> iter = getRepository().findAll().iterator();
		while(iter.hasNext()) {
			final ProductCache cache = iter.next();
			if(cache.getOrderStatusType().equals(entity.getOrderStatusType()) &&
					cache.getProductId().equals(entity.getProductId())) {
				throw new BusinessException(HttpStatus.BAD_REQUEST, getMessage("msg.produto.existente.cache"));
			}
		}
		
		final ProductCache merged = getRepository().save(entity);
		
		log.info("Item salvo no cache => " + merged);
		
		return merged;
	}

	/**
	 * 
	 */
	@Override
	public List<ProductCache> findByOrderStatusType(final OrderStatusType orderStatusType) {
		final Iterator<ProductCache> iter = getRepository().findAll().iterator();
		final List<ProductCache> list = new ArrayList<ProductCache>();
		while(iter.hasNext()) {
			final ProductCache cache = iter.next();
			if(cache != null && cache.getOrderStatusType().equals(orderStatusType)) {
				list.add(cache);
				
			}
		}
		
		return list;
	}

	/**
	 * 
	 */
	@Override
	public void delete(final String id) {
		getRepository().deleteById(id);
	}
	
    /**
     * 
     * @param code
     * @return
     */
	 private String getMessage(final String code) {
		 return messageSource.getMessage(code, null, Locale.getDefault());
	 }
}
