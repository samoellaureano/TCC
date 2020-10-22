package br.com.estacionamento.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.estacionamento.dao.ModeloDAO;
import br.com.estacionamento.entidade.Modelo;

public class ModeloJPADAO extends JPAAbstract<Modelo> implements ModeloDAO{

	@Override
	public String getEntityName() {
		return "Modelo";
	}

	@Override
	public List<Modelo> buscarPorIdMarca(int IdMarca) {
		List<Modelo> listaModelos = new ArrayList<Modelo>();

		String jpql = "select c from "+getEntityName()+" c where c.marca.id =:marca_id ";
		Query query = super.getQuery(jpql);
		query.setParameter("marca_id", IdMarca);
		
		@SuppressWarnings("rawtypes")
		List list = query.getResultList();
		for (Object object: list) {
			listaModelos.add((Modelo) object);
		}
		return listaModelos;
	}

}
