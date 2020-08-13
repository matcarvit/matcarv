/**
 * 
 */
package com.matcarv.products.repository;

import java.io.Serializable;
import java.util.List;

import com.matcarv.products.dtos.ProductFilterDTO;
import com.matcarv.products.dtos.ProductSearchDTO;

/**
 * @author weslleymatosdecarvalho
 *
 */
public interface ProductRepositoryCustom extends Serializable {

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
}
