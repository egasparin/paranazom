package br.edu.utfpr.paranazom.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.paranazom.model.Options;
import br.edu.utfpr.paranazom.repository.OptionsRepository;

@Service
public class OptionsService {

	@Autowired
	private OptionsRepository optionsRepository;

	public Options update(String option_id, Options options) {
		Options optionsSave = findOptionsByCode(option_id);
		
		BeanUtils.copyProperties(options, optionsSave, "options_id"); 
		
		return optionsRepository.save(optionsSave);
	}
	
	public Options findOptionsByCode(String option_id) {
		Optional<Options> optionsSaveOpt = optionsRepository.findById(option_id);
		
		if (!optionsSaveOpt.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		Options optionsSave = optionsSaveOpt.get();
		
		
		return optionsSave;
	}
	
}
