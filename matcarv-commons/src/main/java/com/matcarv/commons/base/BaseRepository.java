/**
 * 
 */
package com.matcarv.commons.base;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author weslleymatosdecarvalho
 *
 */
public interface BaseRepository<E extends BaseEntity<ID>, ID extends Serializable>  
		extends JpaRepository<E, ID>, CrudRepository<E, ID>{

}
