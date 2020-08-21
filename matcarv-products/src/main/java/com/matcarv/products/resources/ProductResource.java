/**
 * 
 */
package com.matcarv.products.resources;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matcarv.products.business.ProductBusiness;
import com.matcarv.products.converters.ProductConverter;
import com.matcarv.products.dtos.ProductFilterDTO;
import com.matcarv.products.dtos.ProductFormDTO;
import com.matcarv.products.dtos.ProductSearchDTO;
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
	 * @param formDTO
	 */
	@PutMapping(path = "/products/persist")
	public ProductFormDTO persist(final ProductFormDTO formDTO) {
		final Product entity = getProductConverter().convertToEntity(formDTO);
		
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
	@PostMapping(path = "/products")
	public List<ProductSearchDTO> findByFilter(final ProductFilterDTO filter) {
		return getProductBusiness().findByFilter(filter);
	}
	
	/**
	 * 
	 * @return
	 */
	@PostMapping(path = "/products/count")
	public Long getCount(final ProductFilterDTO filter) {
		return getProductBusiness().getCount(filter);
	}
}
