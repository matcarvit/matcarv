/**
 * 
 */
package com.matcarv.products.converters;

import org.springframework.stereotype.Component;

import com.matcarv.commons.base.BaseConverter;
import com.matcarv.products.dtos.CategoryFormDTO;
import com.matcarv.products.entities.Category;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Component
public class CategoryConverter extends BaseConverter<Category, CategoryFormDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8996118190515906314L;

	/**
	 * 
	 */
	@Override
	public CategoryFormDTO convertToDTO(final Category entity) {
		final CategoryFormDTO dto = new CategoryFormDTO();
		dto.setId(entity.getId());
		dto.setDescription(entity.getDescription());
		
		return dto;
	}

	/**
	 * 
	 */
	@Override
	public Category convertToEntity(final CategoryFormDTO dto) {
		final Category entity = new Category();
		entity.setId(dto.getId());
		entity.setDescription(dto.getDescription());
		
		return entity;
	}

}
