package br.edu.utfpr.paranazom.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.paranazom.model.User;
import br.edu.utfpr.paranazom.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	public User update(String user_id, User user) {
		User userSave = findUserByCode(user_id);
		
		BeanUtils.copyProperties(user, userSave, "user_id"); 
		
		return userRepository.save(userSave);
	}
	
	public User findUserByCode(String user_id) {
		Optional<User> userSaveOpt = userRepository.findById(user_id);
		
		if (!userSaveOpt.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		User userSave = userSaveOpt.get();
		
		
		return userSave;
	}
	
}