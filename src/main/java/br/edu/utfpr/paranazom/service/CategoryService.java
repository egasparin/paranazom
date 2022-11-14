package br.edu.utfpr.paranazom.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.paranazom.model.Category;
import br.edu.utfpr.paranazom.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public Category update(String category_id, Category category) {
		Category categorySave = findCategoryByCode(category_id);
		
		BeanUtils.copyProperties(category, categorySave, "category_id"); 
		
		return categoryRepository.save(categorySave);
	}
	
	public Category findCategoryByCode(String category_id) {
		Optional<Category> categorySaveOpt = categoryRepository.findById(category_id);
		
		if (!categorySaveOpt.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		Category categorySave = categorySaveOpt.get();
		
		
		return categorySave;
	}
	
}
