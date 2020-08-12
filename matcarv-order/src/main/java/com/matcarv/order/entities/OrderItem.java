/**
 * 
 */
package com.matcarv.order.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.matcarv.commons.base.BaseEntity;
import com.matcarv.commons.enums.OrderStatusType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Data
@Audited
@Entity
@Table(name = "order_items")
@EqualsAndHashCode(callSuper = false, of = "id")
public class OrderItem extends BaseEntity<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5374785795189564693L;

	/**
	 * 
	 */
	@Id
    @Column(name = "id_order_item", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	/**
	 * 
	 */
	@NotAudited
	@ManyToOne
	@JoinColumn(name = "id_order", nullable = false)
	private Order order;
	
	/**
	 * 
	 */
	@Column(name = "id_product", nullable = false)
	private String productId;
	
	/**
	 * 
	 */
	@Column(name = "product_description", nullable = false)
	private String productDesciption;
	
	/**
	 * 
	 */
	@Column(name = "quantity", nullable = false, scale = 3, precision = 7)
	private BigDecimal quantity = new BigDecimal(0);
	
	/**
	 * 
	 */
	@Column(name = "order_status_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatusType orderStatusType = OrderStatusType.CREATED;
}
