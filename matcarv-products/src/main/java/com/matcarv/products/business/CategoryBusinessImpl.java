/**
 * 
 */
package com.matcarv.products.business;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.matcarv.commons.base.AbstractBaseBusinessImpl;
import com.matcarv.commons.enums.TransactionType;
import com.matcarv.commons.exceptions.BusinessException;
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

	/**
	 * 
	 */
	@Override
	protected void processValidate(final Category entity, final TransactionType transactionType) {
		if(StringUtils.isEmpty(entity.getDescription())) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, getMessage("msg.campo.obrigatorio", getMessage("lbl.descricao")));
		}
	}
}
