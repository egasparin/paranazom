package br.edu.utfpr.paranazom.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.paranazom.model.Order;
import br.edu.utfpr.paranazom.model.ProductOrderRelation;
import br.edu.utfpr.paranazom.repository.ProductOrderRelationRepository;

@Service
public class ProductOrderRelationService {
	
	@Autowired
	private ProductOrderRelationRepository productOrderRepository;
	

}
