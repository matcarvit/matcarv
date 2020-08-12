/**
 * 
 */
package com.matcarv.commons.cache.business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matcarv.commons.cache.entities.ProductCache;
import com.matcarv.commons.cache.repository.ProductCacheRepository;
import com.matcarv.commons.enums.OrderStatusType;

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
	
	@Getter
	@Autowired
	private ProductCacheRepository repository;

	/**
	 * 
	 */
	@Override
	public void processInsert(final ProductCache entity) {
		System.out.println(getRepository().save(entity));
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
}
