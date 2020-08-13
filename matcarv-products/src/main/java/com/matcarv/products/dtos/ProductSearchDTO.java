/**
 * 
 */
package com.matcarv.products.dtos;

import java.io.Serializable;

import com.matcarv.commons.enums.ProductType;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Getter
public class ProductSearchDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4408587400080553079L;
	
	/**
	 * 
	 */
	private final String id;
	
	/**
	 * 
	 */
	private final String name;
	
	/**
	 * 
	 */
	private final String description;
	
	/**
	 * 
	 */
	private final String category;
	
	/**
	 * 
	 */	
	private final ProductType type;

	/**
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param category
	 * @param type
	 */
	public ProductSearchDTO(final String id, final String name, final String description, final String category, final ProductType type) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.category = category;
		this.type = type;
	}
	
	

}
