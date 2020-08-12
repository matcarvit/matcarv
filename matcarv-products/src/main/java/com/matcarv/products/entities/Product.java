/**
 * 
 */
package com.matcarv.products.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.matcarv.commons.base.BaseEntity;
import com.matcarv.commons.enums.ProductType;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Data
@Audited
@Entity
@Table(name = "products")
@EqualsAndHashCode(callSuper = false, of = "id")
public class Product extends BaseEntity<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3733416579379557949L;

	/**
	 * 
	 */
	@Id
    @Column(name = "id_product", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	/**
	 * 
	 */
	@NotAudited
	@ManyToOne
	@JoinColumn(name = "id_category", nullable = false)
	private Category category;
	
	/**
	 * 
	 */
	@Column(name = "name", nullable = false)
	private String name;
	
	/**
	 * 
	 */
	@Column(name = "description", nullable = false)
	private String description;
	
	/**
	 * 
	 */
	@Column(name = "product_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private ProductType productType = ProductType.UNIT;
	
	/**
	 * 
	 */
	@Column(name = "quantity", nullable = false, scale = 3, precision = 7)
	private BigDecimal quantity = new BigDecimal(0);
	
	/**
	 * 
	 */
	@Column(name = "product_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date productDate = new Date();
}
