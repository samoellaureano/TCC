package br.com.estacionamento.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.estacionamento.dao.jpa.ClienteJPADAO;
import br.com.estacionamento.dao.jpa.TipoVeiculoJPADAO;
import br.com.estacionamento.dao.jpa.VeiculoJPADAO;
import br.com.estacionamento.entidade.Cliente;
import br.com.estacionamento.entidade.Veiculo;
import br.com.estacionamento.util.UtilRest;

@Path("veiculoRest")
public class VeiculoRest extends UtilRest{

	@POST
	@Path("/addVeiculo")
	@Consumes("application/*")

	public Response inserir(String addVeiculo){

		try {

			Veiculo veiculo = new ObjectMapper().readValue(addVeiculo, Veiculo.class);
			Cliente cliente = veiculo.getCliente();



			VeiculoJPADAO veiculoJpadao = new VeiculoJPADAO();
			ClienteJPADAO clienteJpadao = new ClienteJPADAO();
			TipoVeiculoJPADAO tipoVeiculoJpadao = new TipoVeiculoJPADAO();

			if(cliente.getUsuario() != null) {
				cliente = clienteJpadao.buscarPorIdUsuario(cliente.getUsuario().getId());

				veiculo.setCliente(cliente);
			}
			veiculo.setTipoVeiculo(tipoVeiculoJpadao.buscarPorId(veiculo.getTipoVeiculo().getId()));

			boolean retorno = veiculoJpadao.salvar(veiculo);

			if(retorno){
				// Cadastrado com sucesso.
				return this.buildResponse("1");				

			}else if(retorno==false){
				// ja existe um veiculo
				return this.buildErrorResponse("2");

			}else {
				// Erro ao cadastrar o veiculo
				return this.buildErrorResponse("0");			
			}		

		} catch (Exception e){
			e.printStackTrace();

			return this.buildErrorResponse("Erro ao cadastrar veiculo");
		}

	}// fim do método inserir	

}
