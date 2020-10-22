package br.com.estacionamento.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.estacionamento.dao.jpa.PagamentoJPADAO;
import br.com.estacionamento.entidade.Pagamento;
import br.com.estacionamento.util.UtilRest;

@Path("pagamentoRest")
public class PagamentoRest extends UtilRest{
	
	@POST
	@Path("/addPagamento")
	@Consumes("application/*")

	public Response salvar(String addPagamento){
		try {
			
			Pagamento pagamento = new ObjectMapper().readValue(addPagamento, Pagamento.class);
			
			PagamentoJPADAO pagamentoJpadao = new PagamentoJPADAO();
			
			boolean retorno = pagamentoJpadao.salvar(pagamento);
					
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
			
			return this.buildErrorResponse("Erro ao cadastrar pagamento");
		}
	}

}