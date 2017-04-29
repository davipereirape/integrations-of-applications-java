//
// package br.com.alura.loja;
//
// import javax.ws.rs.client.Client;
// import javax.ws.rs.client.ClientBuilder;
// import javax.ws.rs.client.Entity;
// import javax.ws.rs.client.WebTarget;
// import javax.ws.rs.core.MediaType;
// import javax.ws.rs.core.Response;
// import org.glassfish.grizzly.http.server.HttpServer;
// import org.glassfish.jersey.client.ClientConfig;
// import org.glassfish.jersey.filter.LoggingFilter;
// import org.junit.After;
// import org.junit.Assert;
// import org.junit.Before;
// import org.junit.Test;
// import com.google.gson.Gson;
// import br.com.alura.loja.modelo.Carrinho;
// import br.com.alura.loja.modelo.Produto;
// import br.com.alura.loja.modelo.Projeto;
//
/// **
// * Classe de teste dos serviços disponíveis na aplicação.
// *
// * @author Davi Pereira <pereiradavipe@gmail.com>
// */
// public class ClienteTest
// {
//
// private HttpServer server;
//
// @Before
// public void startsServidor()
// {
// // Antes de cada método, inicia o servidor RS utilizando glassfish / jersey.
// server = Servidor.inicializaServidor();
// }
//
// @After
// public void paraServidor()
// {
// // Para o servidor.
// server.stop();
// }
//
// @Test
// public void testBuscaCarrinho()
// {
// // Podemos receber xml, json, JAX-B (conforme neste exemplo), entre outros, no retorno do nosso web service REST.
// // Neste caso, recebemos um objeto, que utiliza o JAX-B para controlar sua forma de XML e sua forma Objeto.
// // Observe o objeto token, informado no header da requisição. Este, é a forma que o recurso rest irá usar para autenticar.
// WebTarget target = criaCliente();
// Carrinho carrinho = target.path("/carrinhos/1").request().header("token", "TOKEN123").get(Carrinho.class);
//
// // System.out.println(conteudo);
// // Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
// Assert.assertEquals("Rua Vergueiro 3185, 8 andar", carrinho.getRua());
// }
//
// @Test
// public void testBuscaProjetos()
// {
// // Conecta e realiar request na uri informada.
// WebTarget target = criaCliente();
// String conteudo = target.path("/projetos").request().get(String.class);
//
// System.out.println(conteudo);
// // Neste caso, recebemos o retorno Json e convertemos para o objeto.
// Projeto projeto = (Projeto) new Gson().fromJson(conteudo, Projeto.class);
// Assert.assertEquals("Minha loja", projeto.getNome());
// }
//
// @Test
// public void testAdicionaCarrinho()
// {
// // POST, para adicionar um carrinho, utilizamos o web service com recurso POST.
// // O post espera receber uma entity, logo, cliamos nosso xml e adicionamos ao objeto que será enviado.
// WebTarget target = criaCliente();
//
// Carrinho carrinho = new Carrinho();
// carrinho.adiciona(new Produto(314L, "Tablet", 999, 1));
// carrinho.setRua("Rua Vergueiro");
// carrinho.setCidade("Sao Paulo1");
// String xml = carrinho.toXML();
//
// Entity<String> entity = Entity.entity(xml, MediaType.APPLICATION_XML);
//
// // Se tudo ocorrer bem, recebemos o response HTTP 201 (requisição executada com sucesso).
// Response response = target.path("/carrinhos").request().post(entity);
// Assert.assertEquals(response.getStatus(), 201);
//
// String location = response.getHeaderString("Location");
// Client cliente = ClientBuilder.newClient();
// WebTarget target2 = cliente.target(location);
// String xmlCarrinho = target2.request().header("token", "TOKEN123").get(String.class);
// Assert.assertTrue(xmlCarrinho.contains("Sao Paulo1"));
//
// }
//
// /**
// * Cria um webTarget para realizar as operações REST.
// *
// * @return WebTarget
// */
// private WebTarget criaCliente()
// {
// // filtro e logging
// ClientConfig config = new ClientConfig();
// config.register(new LoggingFilter());
// //
//
// Client cliente = ClientBuilder.newClient(config);
// WebTarget target = cliente.target("http://localhost:8080");
// return target;
// }
// }
