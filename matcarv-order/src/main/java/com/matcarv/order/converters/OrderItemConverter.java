/**
 * 
 */
package com.matcarv.order.converters;

import org.springframework.stereotype.Component;

import com.matcarv.commons.base.BaseConverter;
import com.matcarv.order.dtos.OrderItemFormDTO;
import com.matcarv.order.entities.OrderItem;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Component
public class OrderItemConverter extends BaseConverter<OrderItem, OrderItemFormDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4766752539682430297L;

	/**
	 * 
	 */
	@Override
	public OrderItemFormDTO convertToDTO(final OrderItem entity) {
		final OrderItemFormDTO dto = new OrderItemFormDTO();
		dto.setId(entity.getId());
		dto.setProductDesciption(entity.getProductDesciption());
		dto.setProductId(entity.getProductId());
		dto.setQuantity(entity.getQuantity());
		
		return dto;
	}

	/**
	 * 
	 */
	@Override
	public OrderItem convertToEntity(OrderItemFormDTO dto) {
		final OrderItem entity = new OrderItem();
		entity.setId(dto.getId());
		entity.setProductDesciption(dto.getProductDesciption());
		entity.setProductId(dto.getProductId());
		entity.setQuantity(dto.getQuantity());
		
		return entity;
	}

}
