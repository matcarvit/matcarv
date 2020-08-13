/**
 * 
 */
package com.matcarv.products.dtos;

import com.matcarv.commons.base.BaseFilterDTO;
import com.matcarv.commons.enums.ProductType;

import lombok.Getter;
import lombok.Setter;

/**
 * @author weslleymatosdecarvalho
 *
 */
public class ProductFilterDTO extends BaseFilterDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -869856755444748411L;
	
	/**
	 * 
	 */
	@Getter
	@Setter
	private String name;
	
	/**
	 * 
	 */
	@Getter
	@Setter
	private String description;
	
	/**
	 * 
	 */
	@Getter
	@Setter
	private String category;
	
	/**
	 * 
	 */	
	@Getter
	@Setter
	private ProductType productType;

}
