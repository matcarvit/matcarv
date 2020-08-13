/**
 * 
 */
package com.matcarv.commons.base;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author weslleymatosdecarvalho
 *
 */
public class BaseFilterDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4754208673290764461L;

	/**
	 * 
	 */
	@Getter
	@Setter
	private Integer firstResult = 0;
	
	/**
	 * 
	 */
	@Getter
	@Setter
	private Integer maxResults = 8;
}
