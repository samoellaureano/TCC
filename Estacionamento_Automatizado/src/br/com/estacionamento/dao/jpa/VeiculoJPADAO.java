package br.com.estacionamento.dao.jpa;

import br.com.estacionamento.dao.VeiculoDAO;
import br.com.estacionamento.entidade.Veiculo;

public class VeiculoJPADAO extends JPAAbstract<Veiculo> implements VeiculoDAO{

	@Override
	public String getEntityName() {
		return "Veiculo";
	}
	
	
}