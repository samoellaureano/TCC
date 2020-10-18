package br.com.estacionamento.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.estacionamento.dao.jpa.FuncionarioJPADAO;
import br.com.estacionamento.entidade.Funcionario;
import br.com.estacionamento.entidade.Usuario;
import br.com.estacionamento.util.UtilRest;

@Path("funcionarioRest")
public class FuncionarioRest extends UtilRest{
	@POST
	@Path("/addFuncionario")
	@Consumes("application/*")

	public Response salvar(String addFuncionario){

		try {

			Funcionario funcionario = new ObjectMapper().readValue(addFuncionario,Funcionario.class);
			Usuario usuario = funcionario.getUsuario();

			usuario.setAcesso(true);
			usuario.setPerfil(1);
			usuario.setSenhaCriptografada("1234");
			
			funcionario.setUsuario(usuario);
			
			FuncionarioJPADAO funcionarioJpadao = new FuncionarioJPADAO();

			boolean	retorno = funcionarioJpadao.salvar(funcionario);

			if(retorno){
				// true = Cadastrado com sucesso.
				return this.buildResponse("1");				

			}else if(retorno==false){
				// false =  ja existe
				return this.buildErrorResponse("2");

			}else {
				// null = Erro ao cadastrar o 
				return this.buildErrorResponse("0");			
			}

		} catch (Exception e){
			e.printStackTrace();

			return this.buildErrorResponse("Erro ao cadastrar");
		}

	}

}
