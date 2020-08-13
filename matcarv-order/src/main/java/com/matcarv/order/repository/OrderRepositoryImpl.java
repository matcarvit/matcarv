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
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.matcarv.order.dtos.OrderFilterDTO;
import com.matcarv.order.dtos.OrderSearchDTO;
import com.matcarv.order.entities.Order;
import com.matcarv.order.entities.OrderItem;

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
	public List<OrderSearchDTO> findByFilter(final OrderFilterDTO filter) {
		final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<OrderSearchDTO> criteriaQuery = criteriaBuilder.createQuery(OrderSearchDTO.class);
		
		final Root<Order> root = criteriaQuery.from(Order.class);
		final ListJoin<Order, OrderItem> orderItemJoin = root.joinList("items");
		
		final List<Predicate> predicates = prepareFilter(criteriaBuilder, root, orderItemJoin, filter);
		
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
		criteriaQuery.groupBy(root.get("id"));
		
		final TypedQuery<OrderSearchDTO> query = getEntityManager().createQuery(criteriaQuery);
		query.setFirstResult(filter.getFirstResult());
		query.setMaxResults(filter.getMaxResults());
		
		final List<OrderSearchDTO> list = query.getResultList();
		
		return list;
	}
	
	/**
	 * 
	 */
	@Override
	public Long getCount(final OrderFilterDTO filter) {
		final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		
		final Root<Order> root = criteriaQuery.from(Order.class);
		final ListJoin<Order, OrderItem> orderItemJoin = root.joinList("items");
		
		final List<Predicate> predicates = prepareFilter(criteriaBuilder, root, orderItemJoin, filter);
		
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
			final Root<Order> root, 
			final ListJoin<Order, OrderItem> orderItemJoin, 
			final OrderFilterDTO filter) {
		final List<Predicate> predicates = new ArrayList<>();
		
		if (StringUtils.isNotEmpty(filter.getProductDesciption())) {
			predicates.add(criteriaBuilder.like(orderItemJoin.get("productDescription"), "%" + filter.getProductDesciption() + "%"));
		}
		
		if(filter.getOrderStatusType() != null) {
			predicates.add(criteriaBuilder.equal(root.get("orderStatusType"), filter.getOrderStatusType()));
		}
		
		return predicates;
	}

}
