/**
 * 
 */
package com.matcarv.products.business;

import java.util.List;

import com.matcarv.commons.base.BaseBusiness;
import com.matcarv.products.dtos.ProductFilterDTO;
import com.matcarv.products.dtos.ProductSearchDTO;
import com.matcarv.products.entities.Product;

/**
 * @author weslleymatosdecarvalho
 *
 */
public interface ProductBusiness extends BaseBusiness<Product, String> {
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	public List<ProductSearchDTO> findByFilter(final ProductFilterDTO filter);
	
	/**
	 * 
	 * @param filter
	 * @return
	 */
	public Long getCount(final ProductFilterDTO filter);

	/**
	 * 
	 */
	public void processCreatedProductFromCache();
	
	/**
	 * 
	 */
	public void processCancelledProductFromCache();
}
