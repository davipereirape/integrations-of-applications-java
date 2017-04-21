
package br.com.alura.loja.modelo;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

/**
 * Bean de projeto.
 * 
 * @author Davi Pereira <pereiradavipe@gmail.com>
 */
public class Projeto
{

   private Long id;
   private String nome;
   private int anoInicio;

   public Projeto()
   {
   }

   public Projeto(Long id, String nome, int anoInicio)
   {
      this.id = id;
      this.nome = nome;
      this.anoInicio = anoInicio;
   }

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public String getNome()
   {
      return nome;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
   }

   public int getAnoInicio()
   {
      return anoInicio;
   }

   public void setAnoInicio(int anoInicio)
   {
      this.anoInicio = anoInicio;
   }

   public String toXML()
   {
      // Poderiamos usar qualquer biblioteca para transformar os dados de resposta.
      // Podería inclusive, utilizar o JSON, mas de maneira mais simples, o XSTream foi utilizado.

      return new XStream().toXML(this);
   }

   public String toJson()
   {
      return new Gson().toJson(this);
   }

}
