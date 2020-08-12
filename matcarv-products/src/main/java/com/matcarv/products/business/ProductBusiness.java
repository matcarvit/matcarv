/**
 * 
 */
package com.matcarv.products.business;

import com.matcarv.commons.base.BaseBusiness;
import com.matcarv.products.entities.Product;

/**
 * @author weslleymatosdecarvalho
 *
 */
public interface ProductBusiness extends BaseBusiness<Product, String> {

	/**
	 * 
	 */
	public void processCreatedProductFromCache();
	
	/**
	 * 
	 */
	public void processCancelledProductFromCache();
}
