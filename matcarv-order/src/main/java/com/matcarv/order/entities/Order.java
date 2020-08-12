/**
 * 
 */
package com.matcarv.order.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Entity
@Audited
@Table(name = "orders")
@EqualsAndHashCode(callSuper = false, of = "id")
public class Order extends BaseEntity<String>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3113755920169276078L;
	
	/**
	 * 
	 */
	@Id
    @Column(name = "id_order", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	/**
	 * 
	 */
	@Column(name = "order_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date orderDate = new Date();
	
	/**
	 * 
	 */
	@Column(name = "order_status_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private OrderStatusType orderStatusType = OrderStatusType.CREATED;
	
	/**
	 * 
	 */
	@NotAudited
	@OneToMany(mappedBy = "order")
	private List<OrderItem> items;
	
	/**
	 * 
	 * @return
	 */
	public List<OrderItem> getItems() {
		if(items == null) {
			items = new ArrayList<OrderItem>();
		}
		
		return items;
	}
}
