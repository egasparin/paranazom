package br.edu.utfpr.paranazom.repository.search;

import java.util.List;

import br.edu.utfpr.paranazom.model.Product;
import br.edu.utfpr.paranazom.repository.filter.ProductFilter;

public interface ProductRepositoryQuery {
	
	public List<Product> filter(ProductFilter productFilter);
//	public List<Product> porNome(ProductFilter productFilter);
//	public List<Product> porTag(ProductFilter productFilter);
//	public List<Product> porValorMax(ProductFilter productFilter);
//	public List<Product> porValorMin(ProductFilter productFilter);
	
}
