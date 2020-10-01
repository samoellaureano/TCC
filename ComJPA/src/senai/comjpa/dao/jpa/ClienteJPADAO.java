package senai.comjpa.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import senai.comjpa.dao.ClienteDAO;
import senai.comjpa.pojo.Cliente;

public class ClienteJPADAO extends JPAConnection implements ClienteDAO {

	@Override
	public int incluir(Cliente cliente) {
		super.incluir(cliente);
		return cliente.getId();
	}

	@Override
	public Cliente buscarPorId(int id) {
		String jpql = "select c from Cliente c where id = " + id;
		Query query = super.getQuery(jpql);
		@SuppressWarnings("rawtypes")
		List list = query.getResultList();
		for (Object object: list) {
			return ((Cliente) object);
		}
		return null;
	}

}
