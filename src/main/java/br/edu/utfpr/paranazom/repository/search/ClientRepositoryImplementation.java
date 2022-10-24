package br.edu.utfpr.paranazom.repository.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.edu.utfpr.paranazom.model.Client;
import br.edu.utfpr.paranazom.repository.filter.ClientFilter;

import javax.persistence.criteria.Predicate;

public class ClientRepositoryImplementation implements ClientRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Client> pageFilter(ClientFilter clientFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
		Root<Client> root = criteria.from(Client.class);
		
		Predicate[] predicates = criarRestricoes(clientFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Client> query = manager.createQuery(criteria);
		
		addPagesRestrictions(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(clientFilter)) ;

	}
	
	private void addPagesRestrictions(TypedQuery<Client> query, Pageable pageable) {
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	private Long total(ClientFilter clientFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Client> root = criteria.from(Client.class);
		
		Predicate[] predicates = criarRestricoes(clientFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}
	
	/*
	@Override
	public List<Client> listFilter(ClientFilter clientFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
		Root<Client> root = criteria.from(Client.class);
		
		Predicate[] predicates = criarRestricoes(clientFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Client> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	*/
	
	/*
	@Override
	public List<Client> porNome(ClientFilter clientFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
		Root<Client> root = criteria.from(Client.class);
		
		Predicate[] predicates = criarRestricoes(clientFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Client> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	@Override
	public List<Client> porCpf(ClientFilter clientFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = builder.createQuery(Client.class);
		Root<Client> root = criteria.from(Client.class);
		
		Predicate[] predicates = criarRestricoes(clientFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Client> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	*/
	
	@SuppressWarnings("deprecation")
	private Predicate[] criarRestricoes(ClientFilter clientFilter, CriteriaBuilder builder,	Root<Client> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(clientFilter.getName())) {
			predicates.add(builder.like(
					builder.lower(root.get("name")), "%" + clientFilter.getName().toLowerCase() + "%")); 
		}
		
		if (!StringUtils.isEmpty(clientFilter.getCpf())) {
			predicates.add(builder.like(
					builder.lower(root.get("cpf")), "%" + clientFilter.getCpf().toLowerCase() + "%")); 
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

}

		
