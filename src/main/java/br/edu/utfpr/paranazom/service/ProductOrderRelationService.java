package br.edu.utfpr.paranazom.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.paranazom.model.Product;
import br.edu.utfpr.paranazom.model.ProductOrderRelation;
import br.edu.utfpr.paranazom.repository.ProductOrderRelationRepository;

@Service
public class ProductOrderRelationService {

	@Autowired
	private ProductOrderRelationRepository productOrderRepository;

	@Autowired
	private ProductService productService;

	public ProductOrderRelation addProductOrder(ProductOrderRelation po) {

		Product product = productService.findProductByCode(po.getProduct().getProduct_id());
		int qnt = (int) (product.getAmount() - po.getAmount());
		if (qnt < 0) {
			throw new IllegalArgumentException("Quantidade maior que o disponível");
		}
		productService.updateAmount(po.getProduct().getProduct_id(), qnt);

		ProductOrderRelation poSave = productOrderRepository.save(po);
		return poSave;
	}

	public ProductOrderRelation findProductOrder(String product_id, String oreder_id) {
		Optional<ProductOrderRelation> poSaveOpt = productOrderRepository.findBy(null, null);

		if (!poSaveOpt.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}

		ProductOrderRelation poSave = poSaveOpt.get();


		return poSave;

	}

}
