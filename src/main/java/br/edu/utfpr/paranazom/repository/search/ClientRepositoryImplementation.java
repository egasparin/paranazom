package br.edu.utfpr.paranazom.repository.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.util.StringUtils;

import br.edu.utfpr.paranazom.model.Client;
import br.edu.utfpr.paranazom.repository.filter.ClientFilter;

import javax.persistence.criteria.Predicate;

public class ClientRepositoryImplementation implements ClientRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
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
	
	@SuppressWarnings("deprecation")
	private Predicate[] criarRestricoes(ClientFilter clientFilter, CriteriaBuilder builder,	Root<Client> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(clientFilter.getName())) {
			predicates.add(builder.like(
					builder.lower(root.get("Nome: ")), "%" + clientFilter.getName().toLowerCase() + "%")); 
		}
		
		if (!StringUtils.isEmpty(clientFilter.getCpf())) {
			predicates.add(builder.like(
					builder.lower(root.get("CPF: ")), "%" + clientFilter.getCpf().toLowerCase() + "%")); 
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
}

		
