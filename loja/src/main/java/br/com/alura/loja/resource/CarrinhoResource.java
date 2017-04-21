
package br.com.alura.loja.resource;

import java.net.URI;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.thoughtworks.xstream.XStream;
import br.com.alura.loja.dao.CarrinhoDAO;
import br.com.alura.loja.modelo.Carrinho;
import br.com.alura.loja.modelo.Produto;

/**
 * Classe do serviço carrinhos.
 * 
 * @author Curso Alura.
 * @author Davi Pereira <pereiradavipe@gmail.com>
 */
@Path("carrinhos")
public class CarrinhoResource
{

   /**
    * Retorna os dados do carrinho de compras através da chamada get do path configurado no inicio da classe.
    * 
    * @return xml - dados do carrinho de compras.
    */
   @Path("{id}")
   @GET
   @Produces(MediaType.APPLICATION_XML)
   // @Produces(MediaType.APPLICATION_JSON)
   // Retonar JAXB, utirando anotação no carrinho.
   public Carrinho busca(@PathParam("id") long id, @HeaderParam("token") String token)
   {
      // autorização.
      if (!"TOKEN123".equals(token))
      {
         throw new NotAuthorizedException("Token inválido.");
      }

      // Quando colocamos os parâmetros @QueryParam("id") long id, informamos que aceitamos tal parâmetro pela url. url?id=1 por exemplo.
      // Quando colocamos o pathParam com o path, informamos que este método, ou seja, este addressability complementa com /id ou seja /1 ou
      // /2
      // Quando colocar um path, significa que
      // Método que retorna por acesso GET e retorna um MediaType de XML.
      Carrinho carrinho = new CarrinhoDAO().busca(id);
      // return carrinho.toJson();
      return carrinho;
   }

   @POST
   @Consumes(MediaType.APPLICATION_XML) // consome xml.
   public Response adiciona(String conteudo)
   {
      Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
      new CarrinhoDAO().adiciona(carrinho);

      // responde que foi criado com sucesso. Boa prática retorna a localização do que foi criado.
      // A vantagem de retornar status code é que todo mundo usa e conhece o status code.
      URI uri = URI.create("/carrinhos/" + carrinho.getId());
      return Response.created(uri).build();
      // return "<status>sucesso</status>";
   }

   @Path("{id}/produtos/{produtoId}")
   @DELETE
   public Response removeProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId)
   {
      new CarrinhoDAO().busca(id).remove(produtoId);
      return Response.ok().build();
   }

   @Path("{id}/produtos/{produtoId}")
   @PUT
   public Response alteraProduto(String conteudo, @PathParam("id") long id, @PathParam("produtoId") long produtoId)
   {
      Carrinho carrinho = new CarrinhoDAO().busca(id);
      // O conteúdo tem que representar o recurso. Função do PUT
      Produto produto = (Produto) new XStream().fromXML(conteudo);

      carrinho.troca(produto);

      return Response.ok().build();
   }

   @Path("{id}/produtos/{produtoId}/quantidade")
   @PUT
   public Response alteraProdutoQuantidade(String conteudo, @PathParam("id") long id, @PathParam("produtoId") long produtoId)
   {
      // TROCA APENAS A QUANTIDADE.
      Carrinho carrinho = new CarrinhoDAO().busca(id);
      // O conteúdo tem que representar o recurso. Função do PUT
      Produto produto = (Produto) new XStream().fromXML(conteudo);

      carrinho.trocaQuantidade(produto);

      return Response.ok().build();
   }
}
