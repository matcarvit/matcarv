/**
 * 
 */
package com.matcarv.order.resources;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matcarv.order.business.OrderBusiness;
import com.matcarv.order.converters.OrderConverter;
import com.matcarv.order.dtos.OrderFilterDTO;
import com.matcarv.order.dtos.OrderFormDTO;
import com.matcarv.order.dtos.OrderSearchDTO;
import com.matcarv.order.entities.Order;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
@RestController
public class OrderResource {

	/**
	 * 
	 */
	@Getter
	@Autowired
	private OrderBusiness orderBusiness;
	
	/**
	 * 
	 */
	@Getter
	@Autowired
	private OrderConverter orderConverter;
	

	/**
	 * 
	 * @param entity
	 */
	@PutMapping(path = "/orders/persist")
	public OrderFormDTO persist(final Order entity) {
		if(StringUtils.isEmpty(entity.getId())) {
			return getOrderConverter().convertToDTO(getOrderBusiness().processInsert(entity));
		}
		
		return getOrderConverter().convertToDTO(getOrderBusiness().processUpdate(entity));
	}
	
	/**
	 * 
	 * @param id
	 */
	@GetMapping(path = "/orders/delete")
	public void delete(final String id) {
		getOrderBusiness().deleteById(id);
	}
	
	/**
	 * 
	 * @return
	 */
	@PostMapping(path = "/orders")
	public List<OrderSearchDTO> findByFilter(final OrderFilterDTO filter) {
		return getOrderBusiness().findByFilter(filter);
	}
	
	/**
	 * 
	 * @return
	 */
	@PostMapping(path = "/orders/count")
	public Long getCount(final OrderFilterDTO filter) {
		return getOrderBusiness().getCount(filter);
	}
}
