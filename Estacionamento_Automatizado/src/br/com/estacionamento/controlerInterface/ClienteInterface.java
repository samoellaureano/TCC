package br.com.estacionamento.controlerInterface;

import br.com.estacionamento.entidade.Cliente;

public interface ClienteInterface {
	
	public boolean salvar(Cliente cliente);
	public boolean atualizar(Cliente cliente);
	public Cliente buscar(int id);

}
