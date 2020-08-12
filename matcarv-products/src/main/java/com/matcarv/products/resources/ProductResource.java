/**
 * 
 */
package com.matcarv.products.resources;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matcarv.products.business.ProductBusiness;
import com.matcarv.products.converters.ProductConverter;
import com.matcarv.products.dtos.ProductFormDTO;
import com.matcarv.products.entities.Product;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
@RestController
public class ProductResource {
	
	/**
	 * 
	 */
	@Getter
	@Autowired
	private ProductBusiness productBusiness;
	
	/**
	 * 
	 */
	@Getter
	@Autowired
	private ProductConverter productConverter;

	/**
	 * 
	 * @param entity
	 */
	@PutMapping(path = "/products/persist")
	public ProductFormDTO persist(final Product entity) {
		if(StringUtils.isEmpty(entity.getId())) {
			return getProductConverter().convertToDTO(getProductBusiness().processInsert(entity));
		}
		
		return getProductConverter().convertToDTO(getProductBusiness().processUpdate(entity));
	}
	
	/**
	 * 
	 * @param id
	 */
	@GetMapping(path = "/products/delete")
	public void delete(final String id) {
		getProductBusiness().deleteById(id);
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping(path = "/products")
	public List<ProductFormDTO> findAll() {
		return getProductConverter().convertToDTOList(getProductBusiness().findAll());
	}
}
