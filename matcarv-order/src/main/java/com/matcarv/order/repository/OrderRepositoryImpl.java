/**
 * 
 */
package com.matcarv.order.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.matcarv.order.dtos.OrderFilterDTO;
import com.matcarv.order.dtos.OrderSearchDTO;
import com.matcarv.order.entities.Order;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
public class OrderRepositoryImpl implements OrderRepositoryCustom {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8529821076118476661L;
	
	@Getter
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 
	 */
	@Override
	public List<OrderSearchDTO> findByFilter(final OrderFilterDTO filter) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<OrderSearchDTO> criteriaQuery = criteriaBuilder.createQuery(OrderSearchDTO.class);
		
		final Root<Order> root = criteriaQuery.from(Order.class);
		
		final List<Predicate> predicates = prepareFilter(criteriaBuilder, root, filter);
		
		criteriaQuery.select(
				criteriaBuilder.construct(
						OrderSearchDTO.class, 
						root.get("id"),
						root.get("orderDate"),
						root.get("orderStatusType")
				)
		);
		
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
		
		final TypedQuery<OrderSearchDTO> query = entityManager.createQuery(criteriaQuery);
		final List<OrderSearchDTO> list = query.getResultList();
		
		return list;
	}
	
	/**
	 * 
	 */
	@Override
	public Long getCount(final OrderFilterDTO filter) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		
		final Root<Order> root = criteriaQuery.from(Order.class);
		
		final List<Predicate> predicates = prepareFilter(criteriaBuilder, root, filter);
		
		criteriaQuery.select(criteriaBuilder.count(root));
		criteriaQuery.where(predicates.toArray(new Predicate[] {}));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
		
		final TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
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
	private List<Predicate> prepareFilter(final CriteriaBuilder criteriaBuilder, final Root<Order> root, final OrderFilterDTO filter) {
		final List<Predicate> predicates = new ArrayList<>();
		return predicates;
	}

}
