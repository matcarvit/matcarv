/**
 * 
 */
package com.matcarv.products.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.matcarv.commons.base.AbstractBaseBusinessImpl;
import com.matcarv.products.entities.Category;
import com.matcarv.products.repository.CategoryRepository;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Component
@Transactional(propagation = Propagation.SUPPORTS)
public class CategoryBusinessImpl extends AbstractBaseBusinessImpl<Category, String> implements CategoryBusiness{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4956507505987479570L;
	
	@Getter
	@Autowired
	private CategoryRepository repository;
}
