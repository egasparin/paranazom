 package br.edu.utfpr.paranazom.repository.search;
 import java.util.List;

import br.edu.utfpr.paranazom.model.Client;
import br.edu.utfpr.paranazom.repository.filter.ClientFilter;

public interface ClientRepositoryQuery {

	public List<Client> porNome(ClientFilter clientFilter);
	public List<Client> porCpf(ClientFilter clientFilter);
}
