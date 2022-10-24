package br.edu.utfpr.paranazom.repository.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import br.edu.utfpr.paranazom.model.Product;
import br.edu.utfpr.paranazom.repository.filter.ProductFilter;

public class ProductRepositoryImplementation implements ProductRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Product> filter(ProductFilter productFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		
		Predicate[] predicates = criarRestricoes(productFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Product> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	/*
	@Override
	public List<Product> porNome(ProductFilter productFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		
		Predicate[] predicates = criarRestricoes(productFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Product> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	@Override
	public List<Product> porTag (ProductFilter productFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		
		Predicate[] predicates = criarRestricoes(productFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Product> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	@Override
	public List<Product> porValorMax(ProductFilter productFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		
		Predicate[] predicates = criarRestricoes(productFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Product> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	@Override
	public List<Product> porValorMin(ProductFilter productFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
		Root<Product> root = criteria.from(Product.class);
		
		Predicate[] predicates = criarRestricoes(productFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Product> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	*/
	
	@SuppressWarnings("deprecation")
	private Predicate[] criarRestricoes(ProductFilter productFilter, CriteriaBuilder builder,	Root<Product> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(productFilter.getName())) {
			predicates.add(builder.like(
					builder.lower(root.get("nome")), "%" + productFilter.getName().toLowerCase() + "%")); 
		}
		
		if (!StringUtils.isEmpty(productFilter.getTags())) {
			predicates.add(builder.like(
					builder.lower(root.get("tag")), "%" + productFilter.getTags().toLowerCase() + "%")); 
		}
		
		if (productFilter.getPrice() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get("minValue"), productFilter.getPrice())); 
		}
		
		if (productFilter.getPrice() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get("maxValue"), productFilter.getPrice())); 
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}
