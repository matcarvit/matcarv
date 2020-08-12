/**
 * 
 */
package com.matcarv.commons.base;

import java.io.Serializable;

/**
 * @author weslleymatosdecarvalho
 *
 */
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8415236613616177182L;

	/**
	 * 
	 * @return
	 */
	public abstract ID getId();
	
	/**
	 *  s
	 * @param id
	 */
	public abstract void setId(final ID id);
}
