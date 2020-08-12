/**
 * 
 */
package com.matcarv.products.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matcarv.commons.base.BaseConverter;
import com.matcarv.products.dtos.ProductFormDTO;
import com.matcarv.products.entities.Product;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Component
public class ProductConverter extends BaseConverter<Product, ProductFormDTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6229779904250351136L;
	
	/**
	 * 
	 */
	@Getter
	@Autowired
	private CategoryConverter categoryConverter;


	/**
	 * 
	 */
	@Override
	public ProductFormDTO convertToDTO(final Product entity) {
		final ProductFormDTO dto = new ProductFormDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCategory(getCategoryConverter().convertToDTO(entity.getCategory()));
		dto.setDescription(entity.getDescription());
		dto.setProductType(entity.getProductType());
		dto.setProductDate(entity.getProductDate());
		dto.setQuantity(entity.getQuantity());
		
		return dto;
	}

	/**
	 * 
	 */
	@Override
	public Product convertToEntity(final ProductFormDTO dto) {
		final Product entity = new Product();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setCategory(getCategoryConverter().convertToEntity(dto.getCategory()));
		entity.setDescription(dto.getDescription());
		entity.setProductType(dto.getProductType());
		entity.setProductDate(dto.getProductDate());
		entity.setQuantity(dto.getQuantity());
		
		return entity;
	}

}
