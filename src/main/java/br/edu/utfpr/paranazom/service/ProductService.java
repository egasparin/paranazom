package br.edu.utfpr.paranazom.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.paranazom.model.Product;
import br.edu.utfpr.paranazom.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	public Product update(String product_id, Product product) {
		Product productSave = findProductByCode(product_id);
		
		BeanUtils.copyProperties(product, productSave, "product_id"); // Copia os valores dos atributos de pessoa para pessoaSalva, exceto codigo
		//pessoa.setCodigo(codigo);
		
		return productRepository.save(productSave);
	}
	
	public Product findProductByCode(String product_id) {
		Optional<Product> productSaveOpt = productRepository.findById(product_id);
		
		if (!productSaveOpt.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		Product productSave = productSaveOpt.get();
		
		
		return productSave;
	}
	
}