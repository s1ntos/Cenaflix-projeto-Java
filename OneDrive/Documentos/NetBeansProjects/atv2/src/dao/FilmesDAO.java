
package dao;

import beans.Filmes;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class FilmesDAO {
 private Conexao conexao;
 private Connection conn;
 
  public FilmesDAO() {
    this.conexao = new Conexao();
    this.conn = this.conexao.getConexao();
 }
  
public void inserir(Filmes filmes) {
    String sql = "INSERT INTO filmes(nomefilme, categoria, dta) VALUES "
      + "(?, ?, ?)";
    try {
      PreparedStatement stmt = this.conn.prepareStatement(sql);
      stmt.setString(1, filmes.getNomefilme());
      stmt.setString(2, filmes.getCategoria());
      stmt.setString(3, filmes.getDta());
      stmt.execute();
    } catch (Exception e) {
      System.out.println("Erro ao inserir filme: " + e.getMessage());
    }
 }

    public Filmes getFilme (int id){
        String sql = "SELECT * FROM filmes WHERE id = ?";
      try {
 
       PreparedStatement stmt = this.conn.prepareStatement(sql);
       stmt.setInt(1, id);
       ResultSet rs = stmt.executeQuery();
 
       Filmes filme = new Filmes();
 
       rs.next(); 
       filme.setId(id);
       filme.setNomefilme(rs.getString("nomefilme"));
       filme.setCategoria(rs.getString("categoria")); 
       filme.setDta(rs.getString("dta")); 
 
       return filme;
 
 //tratando o erro, caso ele ocorra
    } catch (Exception e) {
      System.out.println("erro: " + e.getMessage());
      return null;
    }
 }
    public void excluir (int id){
 
      String sql = "DELETE FROM filmes WHERE id = ?";
    try {
     //esse trecho é igual ao método editar e inserir
     PreparedStatement stmt = this.conn.prepareStatement(sql);
     stmt.setInt(1, id);
 
      //Executando a query
      stmt.execute();
      //tratando o erro, caso ele ocorra
      } catch (Exception e) {
         System.out.println("Erro ao excluir filme: " + e.getMessage());
    }
 
  }
    
     public void editar (Filmes filmes){
 //string sql com o código de update para o banco de dados
 String sql = "UPDATE filmes SET nomefilme=?, dta=?, categoria=? WHERE id=?";
 try {
 //esse trecho é igual ao método inserir
 PreparedStatement stmt = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
 //Setando os parâmetros
 stmt.setString(1, filmes.getNomefilme());
 stmt.setString(2, filmes.getCategoria());
 stmt.setString(3, filmes.getDta());
 stmt.setInt(4, filmes.getId());
 //Executando a query
 stmt.execute();
 //tratando o erro, caso ele ocorra
 } catch (Exception e) {
 System.out.println("Erro ao editar filme: " + e.getMessage());
 }
 }

    

    
     

     
     
}


