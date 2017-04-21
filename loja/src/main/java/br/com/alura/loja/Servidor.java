
package br.com.alura.loja;

import java.io.IOException;
import java.net.URI;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Classe que irá simular o nosso servidor! Neste projeto, é utilizado o REST através do JERSEY e JAX-RS. É utilizado o grizzly para montar
 * o servidor!
 * 
 * @author Curso Alura.
 * @author Davi Pereira <pereiradavipe@gmail.com>
 */
public class Servidor
{

   public static void main(String[] args) throws IOException
   {
      HttpServer server = inicializaServidor();
      System.out.println("Servidor rodando! ");
      System.in.read();
      server.stop();
   }

   public static HttpServer inicializaServidor()
   {
      ResourceConfig config = new ResourceConfig();
      config.packages("br.com.alura.loja");

      URI uri = URI.create("http://localhost:8080/");
      HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
      return server;
   }
}
