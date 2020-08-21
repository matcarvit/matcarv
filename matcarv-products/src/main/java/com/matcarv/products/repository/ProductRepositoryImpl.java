/**
 * 
 */
package com.matcarv.products.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.matcarv.products.dtos.ProductFilterDTO;
import com.matcarv.products.dtos.ProductSearchDTO;
import com.matcarv.products.entities.Category;
import com.matcarv.products.entities.Product;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
public class ProductRepositoryImpl implements ProductRepositoryCustom {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8683220095766114663L;
	
	/**
	 * 
	 */
	@Getter
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 
	 */
	@Override
	public List<ProductSearchDTO> findByFilter(final ProductFilterDTO filter) {
		final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<ProductSearchDTO> criteriaQuery = criteriaBuilder.createQuery(ProductSearchDTO.class);
		
		final Root<Product> root = criteriaQuery.from(Product.class);
		final Join<Product, Category> categoryJoin = root.join("category");
		
		final List<Predicate> predicates = prepareFilter(criteriaBuilder, root, categoryJoin, filter);
		
		criteriaQuery.select(
				criteriaBuilder.construct(
						ProductSearchDTO.class, 
						root.get("id"),
						root.get("name"),
						root.get("description"),
						categoryJoin.get("description"),
						root.get("quantity"),
						root.get("productType")
				)
		);
		
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
		
		final TypedQuery<ProductSearchDTO> query = getEntityManager().createQuery(criteriaQuery);
		query.setFirstResult(filter.getFirstResult());
		query.setMaxResults(filter.getMaxResults());
		
		final List<ProductSearchDTO> list = query.getResultList();
		
		return list;
	}
	
	/**
	 * 
	 */
	@Override
	public Long getCount(final ProductFilterDTO filter) {
		final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		
		final Root<Product> root = criteriaQuery.from(Product.class);
		final Join<Product, Category> categoryJoin = root.join("category");
		
		final List<Predicate> predicates = prepareFilter(criteriaBuilder, root, categoryJoin, filter);
		
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		
		final TypedQuery<Long> query = getEntityManager().createQuery(criteriaQuery);
		final Long count = query.getSingleResult();
		
		return count;
	}

	/**
	 * 
	 * @param criteriaBuilder
	 * @param root
	 * @param filter
	 * @return
	 */
	private List<Predicate> prepareFilter(
			final CriteriaBuilder criteriaBuilder, 
			final Root<Product> root, 
			final Join<Product, Category> categoryJoin, 
			final ProductFilterDTO filter) {
		final List<Predicate> predicates = new ArrayList<>();
		
		if (StringUtils.isNotEmpty(filter.getName())) {
			predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));
		}
		
		if (StringUtils.isNotEmpty(filter.getDescription())) {
			predicates.add(criteriaBuilder.like(root.get("description"), "%" + filter.getDescription() + "%"));
		}
		
		if (StringUtils.isNotEmpty(filter.getCategory())) {
			predicates.add(criteriaBuilder.like(categoryJoin.get("description"), "%" + filter.getCategory() + "%"));
		}
		
		if(filter.getProductType() != null) {
			predicates.add(criteriaBuilder.equal(root.get("productType"), filter.getProductType()));
		}
		
		return predicates;
	}

}
