/**
 * 
 */
package com.matcarv.commons.base;

import java.io.Serializable;
import java.util.List;

/**
 * @author weslleymatosdecarvalho
 *
 */
public interface BaseBusiness<E extends BaseEntity<ID>, ID extends Serializable> extends Serializable {

	/**
	 * 
	 * @param entity
	 */
	public E processInsert(final E dto);
	
	/**
	 * 
	 * @param entity
	 */
	public E processUpdate(final E dto);
	
	/**
	 * 
	 * @param id
	 */
	public void deleteById(final ID id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public E find(final ID id);
	
	/**
	 * 
	 * @return
	 */
	public List<E> findAll();
	
	/**
	 * 
	 * @param <T>
	 * @param source
	 * @param targetType
	 * @return
	 */
	public <T> T convert(final Object source, final Class<T> targetType);
}
