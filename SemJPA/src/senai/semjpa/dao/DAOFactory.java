package senai.semjpa.dao;

import senai.semjpa.dao.jdbc.CidadeJDBCDAO;
import senai.semjpa.dao.jdbc.ClienteJDBCDAO;
import senai.semjpa.dao.jdbc.EstadoJDBCDAO;

public class DAOFactory {
	
	/*
	 * Factory, do DaoFactory, � um �facilitador�, quando voc� precisa de uma
	 * inst�ncia de algum objeto como por exemplo uma conex�o de banco, usa-se um
	 * Factory, pois atrav�s de um m�todo apenas, voc� j� recebe essa inst�ncia j�
	 * aberta e devidamente configurada� Isso tudo se faz dentro do Factory, ou
	 * seja, voc� poupa ficar escrevendo c�digo toda vez que precisa abrir uma
	 * conex�o�
	 */
	
	@SuppressWarnings("rawtypes")
	public static Object getInstanceOf(Class c) {
		if ( c.equals(ClienteDAO.class) ) {
			return new ClienteJDBCDAO();
		} else if ( c.equals(CidadeDAO.class) ) {
			return new CidadeJDBCDAO();
		} else if ( c.equals(EstadoDAO.class) ) {
			return new EstadoJDBCDAO();
		}
		return null;
	}
}
