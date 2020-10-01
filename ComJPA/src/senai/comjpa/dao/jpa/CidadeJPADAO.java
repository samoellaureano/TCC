package senai.comjpa.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import senai.comjpa.dao.CidadeDAO;
import senai.comjpa.pojo.Cidade;

public class CidadeJPADAO extends JPAConnection implements CidadeDAO{

	@Override
	public int incluir(Cidade cidade) {
		super.incluir(cidade);
		return cidade.getId();
	}

	@Override
	public Cidade buscarPorId(int id) {
		String jpql = "select c from Cidade c where id = " + id;
		Query query = super.getQuery(jpql);
		@SuppressWarnings("rawtypes")
		List list = query.getResultList();
		for (Object object : list) {
			return ((Cidade) object);
		}
		return null;
	}

}
