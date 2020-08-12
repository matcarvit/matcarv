/**
 * 
 */
package com.matcarv.products.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
public class ProductRepositoryImpl implements ProductRepositoryCustom {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8683220095766114663L;
	
	@Getter
	@PersistenceContext
	private EntityManager entityManager;

}
