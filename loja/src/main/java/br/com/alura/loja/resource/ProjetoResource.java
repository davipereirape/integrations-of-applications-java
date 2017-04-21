
package br.com.alura.loja.resource;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;
import br.com.alura.loja.dao.ProjetoDAO;
import br.com.alura.loja.modelo.Projeto;

/**
 * Classe do serviço projeto.
 * 
 * @author Curso Alura.
 * @author Davi Pereira <pereiradavipe@gmail.com>
 */
@Path("projetos")
public class ProjetoResource
{

   /**
    * Retorna os dados do projeto através da chamada get do path configurado no inicio da classe.
    * 
    * @return xml - dados do projeto de compras.
    */
   @GET
   // @Produces(MediaType.APPLICATION_XML)
   @Produces(MediaType.APPLICATION_JSON)
   public String busca()
   {
      // public String busca(@QueryParam("id") long id) // requisição get
      // Método que retorna por acesso GET e retorna um MediaType de XML.
      Projeto projeto = new ProjetoDAO().busca(1l);
      // return projeto.toXML();
      return projeto.toJson();
   }

   @POST
   @Produces(MediaType.APPLICATION_XML)
   public String adiciona(String conteudo)
   {
      Projeto projeto = new Gson().fromJson(conteudo, Projeto.class);
      new ProjetoDAO().adiciona(projeto);

      return "<status>sucesso</status>";
   }

   @Path("{id}")
   @DELETE
   public Response removeProjeto(@PathParam("id") long id)
   {
      new ProjetoDAO().remove(id);
      return Response.ok().build();
   }
}
