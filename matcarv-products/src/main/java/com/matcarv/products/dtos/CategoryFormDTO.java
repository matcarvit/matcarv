/**
 * 
 */
package com.matcarv.products.dtos;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Data
@EqualsAndHashCode(callSuper = false, of = "id")
public class CategoryFormDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7038255466847241357L;

	/**
	 * 
	 */
	private String id;
	
	/**
	 * 
	 */
	private String description;
}
