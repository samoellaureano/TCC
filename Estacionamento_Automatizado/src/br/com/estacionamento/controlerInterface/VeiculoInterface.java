package br.com.estacionamento.controlerInterface;

import br.com.estacionamento.entidade.Veiculo;

public interface VeiculoInterface {
	public boolean salvar(Veiculo veiculo);
	public boolean atualizar(Veiculo veiculo);
	public Veiculo buscar(int id);

}
