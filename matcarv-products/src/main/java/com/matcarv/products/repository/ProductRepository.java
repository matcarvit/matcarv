/**
 * 
 */
package com.matcarv.products.repository;

import com.matcarv.commons.base.BaseRepository;
import com.matcarv.products.entities.Product;

/**
 * @author weslleymatosdecarvalho
 *
 */
public interface ProductRepository extends BaseRepository<Product, String>, ProductRepositoryCustom {

}
