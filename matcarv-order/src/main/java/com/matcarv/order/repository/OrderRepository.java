/**
 * 
 */
package com.matcarv.order.repository;

import com.matcarv.commons.base.BaseRepository;
import com.matcarv.order.entities.Order;

/**
 * @author weslleymatosdecarvalho
 *
 */
public interface OrderRepository extends BaseRepository<Order, String>, OrderRepositoryCustom {

}
