package br.com.estacionamento.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.estacionamento.dao.jpa.EmpresaJPADAO;
import br.com.estacionamento.entidade.Empresa;
import br.com.estacionamento.util.UtilRest;

@Path("empresaRest")
public class EmpresaRest extends UtilRest{
	
	@POST
	@Path("/addEmpresa")
	@Consumes("application/*")

	public Response salvar(String addEmpresa){

		try {

			Empresa empresa = new ObjectMapper().readValue(addEmpresa,Empresa.class);
			empresa.setAtivo(true);
			boolean	retorno = new EmpresaJPADAO().salvar(empresa);

			if(retorno){
				// true = Cadastrado com sucesso.
				return this.buildResponse("1");				

			}else if(retorno==false){
				// false = ja existe
				return this.buildErrorResponse("2");

			}else {
				// null = Erro ao cadastrar
				return this.buildErrorResponse("0");			
			}

		} catch (Exception e){
			e.printStackTrace();

			return this.buildErrorResponse("Erro ao cadastrar");
		}

	}
	
	@POST
	@Path("/buscaEmpresas")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response buscaEmpresas(){
		try{
			List<Empresa> listaEmpresas = new EmpresaJPADAO().buscarPorDescricao("null");
			
			return this.buildResponse(listaEmpresas);
		}catch (Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@POST
	@Path("/buscarEmpresaPorId/{idEmpresa}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response buscarEmpresaPorId(@PathParam("idEmpresa") int idEmpresa){
		try{
			Empresa empresa = new EmpresaJPADAO().buscarPorId(idEmpresa);
			
			return this.buildResponse(empresa);
		}catch (Exception e){
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@POST
	@Path("/editEmpresa")
	@Consumes("application/*")

	public Response editEmpresa(String editEmpresa){

		try {

			Empresa empresa = new ObjectMapper().readValue(editEmpresa,Empresa.class);
			EmpresaJPADAO empresaJpadao = new EmpresaJPADAO();
			empresa.setAtivo(true);
			boolean	retorno = empresaJpadao.atualizar(empresa);

			if(retorno){
				// true = Cadastrado com sucesso.
				return this.buildResponse("1");				

			}else if(retorno==false){
				// false = ja existe
				return this.buildErrorResponse("2");

			}else {
				// null = Erro ao cadastrar
				return this.buildErrorResponse("0");			
			}

		} catch (Exception e){
			e.printStackTrace();

			return this.buildErrorResponse("Erro ao cadastrar");
		}

	}
	@POST
	@Path("/inativaEmpresa/{idEmpresa}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response inativaFuncionario(@PathParam("idEmpresa") int idEmpresa){

		try {
			Empresa empresa = new EmpresaJPADAO().buscarPorId(idEmpresa);
			empresa.setAtivo(false);
			boolean	retorno = false;
			
			retorno = new EmpresaJPADAO().atualizar(empresa);
			
			if(retorno){
				
				return this.buildResponse("1");				
			}else{
				
				return this.buildResponse("2");
			}
			

		} catch (Exception e){
			e.printStackTrace();

			return this.buildErrorResponse("Erro ao atualizar Funcionario");
		}
	}

}
