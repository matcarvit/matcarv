/**
 * 
 */
package com.matcarv.order.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.matcarv.commons.base.BaseConverter;
import com.matcarv.order.dtos.OrderFormDTO;
import com.matcarv.order.entities.Order;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Component
public class OrderConverter extends BaseConverter<Order, OrderFormDTO>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6229779904250351136L;

	@Getter
	@Autowired
	private OrderItemConverter orderItemConverter;

	/**
	 * 
	 */
	@Override
	public OrderFormDTO convertToDTO(final Order entity) {
		final OrderFormDTO dto = new OrderFormDTO();
		dto.setId(entity.getId());
		dto.setOrderDate(entity.getOrderDate());
		dto.setOrderStatusType(entity.getOrderStatusType());
		dto.setItems(getOrderItemConverter().convertToDTOList(entity.getItems()));
		
		return dto;
	}

	/**
	 * 
	 */
	@Override
	public Order convertToEntity(final OrderFormDTO dto) {
		final Order entity = new Order();
		entity.setId(dto.getId());
		entity.setOrderStatusType(dto.getOrderStatusType());
		entity.setItems(getOrderItemConverter().convertToEntityList(dto.getItems()));
		
		return entity;
	}

}
