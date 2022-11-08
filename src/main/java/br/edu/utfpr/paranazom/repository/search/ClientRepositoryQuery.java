package br.edu.utfpr.paranazom.repository.search;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.edu.utfpr.paranazom.model.Client;
import br.edu.utfpr.paranazom.repository.filter.ClientFilter;

public interface ClientRepositoryQuery {

	public Page<Client> pageFilter(ClientFilter clientFilter, Pageable pageable);
//	public List<Client> listFilter(ClientFilter clientFilter);
//	public List<Client> porNome(ClientFilter clientFilter);
//	public List<Client> porCpf(ClientFilter clientFilter);
}