package br.edu.utfpr.paranazom.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.utfpr.paranazom.model.Client;
import br.edu.utfpr.paranazom.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;

	public Client update(String client_id, Client client) {
		Client clientSave = findClientByCode(client_id);
		
		BeanUtils.copyProperties(client, clientSave, "client_id"); 	
		return clientRepository.save(clientSave);
	}
	
	public Client findClientByCode(String client_id) {
		Optional<Client> clientSaveOpt = clientRepository.findById(client_id);
		
		if (!clientSaveOpt.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		Client clientSave = clientSaveOpt.get();
		
		
		return clientSave;
	}
	
}