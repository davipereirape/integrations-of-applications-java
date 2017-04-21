
package br.com.alura.loja.modelo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Bean do produto.
 * 
 * @author Curso Alura.
 * @author Davi Pereira <pereiradavipe@gmail.com>
 */
@XmlRootElement // JAX-B, Necessita de um construtor sem argumentos.
@XmlAccessorType(XmlAccessType.FIELD) // Todos os campos são serializados
public class Produto
{

   private double preco;
   private long id;
   private String nome;
   private int quantidade;

   public Produto()
   {
   }

   public Produto(long id, String nome, double preco, int quantidade)
   {
      this.id = id;
      this.nome = nome;
      this.preco = preco;
      this.quantidade = quantidade;
   }

   public double getPreco()
   {
      return preco;
   }

   public long getId()
   {
      return id;
   }

   public String getNome()
   {
      return nome;
   }

   public int getQuantidade()
   {
      return quantidade;
   }

   public double getPrecoTotal()
   {
      return quantidade * preco;
   }

   public void setQuantidade(int quantidade)
   {
      this.quantidade = quantidade;
   }
}
