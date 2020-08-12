/**
 * 
 */
package com.matcarv.products.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import com.matcarv.commons.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Data
@Entity
@Audited
@Table(name = "categories")
@EqualsAndHashCode(callSuper = false, of = "id")
public class Category extends BaseEntity<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 255756385753167018L;
	
	/**
	 * 
	 */
	@Id
    @Column(name = "id_category", nullable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;
	
	/**
	 * 
	 */
	@Column(name = "description", nullable = false)
	private String description;

}
