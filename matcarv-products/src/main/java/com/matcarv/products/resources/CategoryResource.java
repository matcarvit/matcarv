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

import com.matcarv.products.business.CategoryBusiness;
import com.matcarv.products.converters.CategoryConverter;
import com.matcarv.products.dtos.CategoryFormDTO;
import com.matcarv.products.entities.Category;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
@RestController
public class CategoryResource {

	/**
	 * 
	 */
	@Getter
	@Autowired
	private CategoryBusiness categoryBusiness;
	
	/**
	 * 
	 */
	@Getter
	@Autowired
	private CategoryConverter categoryConverter;
	
	/**
	 * 
	 * @param entity
	 */
	@PutMapping(path = "/categories/persist")
	public CategoryFormDTO persist(final Category entity) {
		if(StringUtils.isEmpty(entity.getId())) {
			return getCategoryConverter().convertToDTO(getCategoryBusiness().processInsert(entity));
		}
		
		return getCategoryConverter().convertToDTO(getCategoryBusiness().processUpdate(entity));
	}
	
	/**
	 * 
	 * @param id
	 */
	@GetMapping(path = "/categories/delete")
	public void delete(final String id) {
		getCategoryBusiness().deleteById(id);
	}
	
	/**
	 * 
	 * @return
	 */
	@GetMapping(path = "/categories")
	public List<CategoryFormDTO> findAll() {
		return getCategoryConverter().convertToDTOList(getCategoryBusiness().findAll());
	}
}
