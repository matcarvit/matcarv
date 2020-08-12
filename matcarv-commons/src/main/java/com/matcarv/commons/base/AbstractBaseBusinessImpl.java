/**
 * 
 */
package com.matcarv.commons.base;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.matcarv.commons.enums.TransactionType;
import com.matcarv.commons.exceptions.BusinessException;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
public abstract class AbstractBaseBusinessImpl<E extends BaseEntity<ID>, ID extends Serializable> implements BaseBusiness<E, ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5924112687968018414L;
	
	/**
	 * 
	 */
	private static final Log log = LogFactory.getLog(AbstractBaseBusinessImpl.class);
	
	/**
	 * 
	 */
	@Getter
	@Autowired
	private MessageSource messageSource;


	/**
	 * 
	 * @param entity
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public E processInsert(final E entity) {
		return insertOrUpdate(entity, TransactionType.INSERT);
	}
	
	/**
	 * 
	 * @param entity
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public E processUpdate(final E entity) {
		return insertOrUpdate(entity, TransactionType.UPDATE);
	}
	
	/**
	 * 
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void deleteById(final ID id) {
		try {
			getRepository().deleteById(id);
		} catch(RuntimeException e) {
			log.error("Erro ao excluir o objeto...", e);
		} 
		
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public E find(final ID id) {
		E entity = null;

		try {
			entity = getRepository().getOne(id);
		} catch(RuntimeException e) {
			log.error("Erro ao buscar o objeto por id...", e);
		}
		
		return entity;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public List<E> findAll() {
		return getRepository().findAll();
	}
	
	/**
	 * 
	 * @param entity
	 * @param type
	 */
	protected E insertOrUpdate(final E entity, final TransactionType type) {
		final E merged = getRepository().save(entity);
		
		return merged;
	}
	
    /**
     * 
     * @param code
     * @return
     */
	 protected String getMessage(final String code) {
		 return messageSource.getMessage(code, null, Locale.getDefault());
	 }
	 

	 /**
	  * 
	  * @param code
	  * @param params
	  * @return
	  */
	 protected String getMessage(final String code, final String... params) {
		 return messageSource.getMessage(code, params, Locale.getDefault());
	 }
	
	/**
	 * 
	 * @return
	 */
	protected abstract BaseRepository<E, ID> getRepository();
	
	 
	/**
	 * 
	 * @param <T>
	 * @param source
	 * @param targetType
	 * @return
	 */
	@Override
	public <T> T convert(final Object source, final Class<T> targetType) {
        if (source == null || targetType == null) {
        	throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Os campos source e targetType são obritórios...");
        }
        
        T target = null;
        
        try {
        	target = targetType.newInstance();
        	BeanUtils.copyProperties(source, target);
        	
		} catch (Exception e) {
			log.error("Erro ao gerar a conversao dos objetos...", e);
		}
        
        return target;
	 }

}
