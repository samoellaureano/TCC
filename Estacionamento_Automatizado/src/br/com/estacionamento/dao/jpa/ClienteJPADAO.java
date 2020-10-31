package br.com.estacionamento.dao.jpa;

import br.com.estacionamento.dao.ClienteDAO;
import br.com.estacionamento.entidade.Cliente;

public class ClienteJPADAO extends JPAAbstract<Cliente> implements ClienteDAO{

	@Override
	public String getEntityName() {
		return "Cliente";
	}
	
}
