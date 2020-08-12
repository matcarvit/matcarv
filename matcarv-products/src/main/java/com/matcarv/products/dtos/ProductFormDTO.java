/**
 * 
 */
package com.matcarv.products.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.matcarv.commons.enums.ProductType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Data
@EqualsAndHashCode(callSuper = false, of = "id")
public class ProductFormDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2719965411969439458L;
	
	/**
	 * 
	 */
	private String id;
	
	/**
	 * 
	 */
	private CategoryFormDTO category;
	
	/**
	 * 
	 */
	private String name;
	
	/**
	 * 
	 */
	private String description;
	
	/**
	 * 
	 */
	private ProductType productType;
	
	/**
	 * 
	 */
	private BigDecimal quantity;
	
	/**
	 * 
	 */
	private Date productDate;

}
