package livraria.Model;

import java.sql.*;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import static livraria.View.janelaAlteraAutor.aaut;
import static livraria.View.janelaAlteraEditora.apub;
import static livraria.View.janelaAlteraLivro.alaut;
import static livraria.View.janelaAlteraLivro.albok;
import static livraria.View.janelaAlteraLivro.alpub;
import static livraria.View.janelaBuscaAutor.autor;
import static livraria.View.janelaBuscaAutor.modeloBuscaAutor;
import static livraria.View.janelaBuscaEditora.edit;
import static livraria.View.janelaBuscaEditora.modeloBuscaEditora;
import static livraria.View.janelaBuscaLivro.livro;
import static livraria.View.janelaBuscaLivro.modeloBuscaLivro;
import static livraria.View.janelaInsereAutor.campoAutor_id;
import static livraria.View.janelaInsereAutor.iaut;
import static livraria.View.janelaInsereEditora.campoEditora_id;
import static livraria.View.janelaInsereEditora.ipub;
import static livraria.View.janelaInsereLivro.ilaut;
import static livraria.View.janelaInsereLivro.ilbok;
import static livraria.View.janelaInsereLivro.ilpub;
import static livraria.View.janelaInsereLivro.listId_autor;
import static livraria.View.janelaInsereLivro.listId_editora;
import static livraria.View.janelaInsereLivro.listNome_autor;
import static livraria.View.janelaInsereLivro.listNome_editora;

/**
 *
 * @author thaismor
 */
public class ActionsBanco {

    public static void criaBuscaAutor(String keyWord){
        try{

            String buscaAutor = "SELECT author_id, name, fname" + 
                                          " FROM authors" + 
                                          " WHERE LOWER(name) LIKE LOWER(?)" +
                                          " OR LOWER(fname) LIKE LOWER (?);";

            Connection conn = FabricaConexao.getConexao();
            PreparedStatement ps = conn.prepareStatement(buscaAutor);
            ps.setString(1, "%"+keyWord+"%");
            ps.setString(2, "%"+keyWord+"%");
            ResultSet rs = ps.executeQuery();
            modeloBuscaAutor.setNumRows(0);

            while(rs.next()){
              modeloBuscaAutor.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
            conn.close();
          }

          catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro de SQL, verifique as logs do sistema!");
          }
    }
    
    public static void criaBuscaLivro(String keyWord){
    try{
        
        String buscaLivro = "SELECT books.title, books.isbn, books.price, publishers.name, publishers.publisher_id, authors.name, booksauthors.author_id" + 
                                      " FROM books, publishers, booksauthors, authors" + 
                                      " WHERE books.publisher_id = publishers.publisher_id" + 
                                      " AND books.isbn = booksauthors.isbn" + 
                                      " AND authors.author_id = booksauthors.author_id" +
                                      " AND LOWER(books.title) LIKE LOWER(?);";
        
        Connection conn = FabricaConexao.getConexao();
        PreparedStatement ps = conn.prepareStatement(buscaLivro);
        ps.setString(1, "%"+keyWord+"%");
        ResultSet rs = ps.executeQuery();
        modeloBuscaLivro.setNumRows(0);

        while(rs.next()){
            modeloBuscaLivro.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getDouble("price"), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});
        }
        conn.close();
      }

      catch(Exception e){
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Ocorreu um Erro de SQL, verifique as logs do sistema!");
      }
    }
    
    public static void criaBuscaEditora(String keyWord){
        
        try{
        
            String buscaEditora = "SELECT publisher_id, name, url" + 
                                            " FROM publishers" + 
                                            " WHERE LOWER(name) LIKE LOWER(?);";

            Connection conn = FabricaConexao.getConexao();
            PreparedStatement ps = conn.prepareStatement(buscaEditora);
            ps.setString(1, "%"+keyWord+"%");
            ResultSet rs = ps.executeQuery();
            modeloBuscaEditora.setNumRows(0);

            while(rs.next()){
              modeloBuscaEditora.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
            conn.close();
          }

          catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ocorreu um Erro de SQL, verifique as logs do sistema!");
          }
    }
    
     public static void cadastraIdEditora() throws SQLException, ClassNotFoundException{
         String idEditora = "SELECT MAX(publisher_id)+1 FROM Publishers";
         Connection conn = FabricaConexao.getConexao();
         PreparedStatement ps = conn.prepareStatement(idEditora);
         ResultSet rs = ps.executeQuery();
         while(rs.next()){
         campoEditora_id.setText(rs.getString(1));
      }
        
    }

    public static void cadastraIdAutor() throws SQLException, ClassNotFoundException {
        String idAutor = "SELECT MAX(author_id)+1 FROM Authors";
        Connection conn = FabricaConexao.getConexao();
        PreparedStatement ps = conn.prepareStatement(idAutor);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
        campoAutor_id.setText(rs.getString(1));
      }
        
    }
    
    public void insereLivro() throws SQLException, ClassNotFoundException
    {
        Connection conn = FabricaConexao.getConexao();
	Statement insert = conn.createStatement();
        insert.executeUpdate("INSERT INTO books (title,isbn,publisher_id,price) VALUES ('" + ilbok.getTitle() + "','" + ilbok.getIsbn() + "','"+ ilpub.getPublisher_id() +"', '" + ilbok.getPrice() +"');"
	+ "INSERT INTO booksauthors (isbn, author_id, seq_no) VALUES ('"+ ilbok.getIsbn() +"','"+ ilaut.getAuthor_id()+"', '1');");
	conn.close();
    }
    
    public void insereAutor() throws SQLException, ClassNotFoundException
    {
        Connection conn = FabricaConexao.getConexao();
	Statement insert = conn.createStatement();
	insert.executeUpdate("INSERT INTO authors (author_id,name,fname) VALUES ((nextval('authors_author_id_seq')),'" + iaut.getName() + "','" + iaut.getFname() +"');");
	conn.close();
    }
    
    public void insereEditora() throws SQLException, ClassNotFoundException
    {
        Connection conn = FabricaConexao.getConexao();
	Statement insert = conn.createStatement();
	insert.executeUpdate("INSERT INTO publishers (publisher_id,name,url) VALUES ((nextval('publishers_publisher_id_seq')),'" + ipub.getName() + "','" + ipub.getUrl() +"')");
	conn.close();
    }
    
    public void atualizaLivro() throws SQLException, ClassNotFoundException
    {
        Connection conn = FabricaConexao.getConexao();
	Statement update = conn.createStatement();
	update.executeUpdate("UPDATE books SET title = '" + albok.getTitle() + "', publisher_id = '"+ alpub.getPublisher_id() +"', price = '"+ albok.getPrice() +"' WHERE isbn = '"+ albok.getIsbn() +"';"
        + "UPDATE booksauthors SET author_id = '"+ alaut.getAuthor_id() +"' WHERE isbn = '"+ albok.getIsbn() +"';");
	conn.close();
    }
    
    
    public void atualizaAutor() throws SQLException, ClassNotFoundException
    {
        Connection conn = FabricaConexao.getConexao();
	Statement update = conn.createStatement();
	update.executeUpdate("UPDATE authors SET name = '" + aaut.getName() + "', fname = '"+ aaut.getFname() +"' WHERE author_id = '"+ aaut.getAuthor_id() +"';");
	conn.close();
    }
    
    public void atualizaEditora() throws SQLException, ClassNotFoundException
    {
        Connection conn = FabricaConexao.getConexao();
	Statement update = conn.createStatement();
	update.executeUpdate("UPDATE publishers SET name = '" + apub.getName() + "', url = '"+ apub.getUrl() +"' WHERE publisher_id = '"+ apub.getPublisher_id() +"';");
	conn.close();
    }
    
    public void excluirLivro() throws SQLException, ClassNotFoundException
    {
        Connection conn = FabricaConexao.getConexao();
	Statement delete = conn.createStatement();
	delete.executeUpdate("DELETE FROM booksauthors WHERE isbn = '"+ livro.getIsbn() +"';"
	+ "DELETE FROM books WHERE isbn = '"+ livro.getIsbn() +"';");
	conn.close();
    }
    
    public void excluirAutor() throws SQLException, ClassNotFoundException
    {
        Connection conn = FabricaConexao.getConexao();
	Statement delete = conn.createStatement();
	delete.executeUpdate("DELETE FROM booksauthors WHERE author_id = '"+ autor.getAuthor_id() +"';"
                + " DELETE FROM authors WHERE author_id = '"+ autor.getAuthor_id() +"';");
	conn.close();
    }
    
    public void excluirEditora() throws SQLException, ClassNotFoundException
    {
        Connection conn = FabricaConexao.getConexao();
	Statement delete = conn.createStatement();
	delete.executeUpdate("DELETE FROM books WHERE publisher_id = '"+ edit.getPublisher_id() +"';"
                + " DELETE FROM publishers WHERE publisher_id = '"+ edit.getPublisher_id() +"';");
	conn.close();
    }
    
    public static void comboboxAutor() throws SQLException, ClassNotFoundException{
        
        Connection conn = FabricaConexao.getConexao();
        String query1 = "SELECT author_id, name FROM authors;";
        PreparedStatement ps = conn.prepareStatement(query1);
        ResultSet rs = ps.executeQuery();

        while(rs.next()){
            listNome_autor.add(rs.getString(2));
            listId_autor.add(rs.getString(1));
        }
        ps.close();
        listNome_autor.add(0, null);
        listId_autor.add(0, null);
            
    }
    
    public static void comboboxEditora() throws SQLException, ClassNotFoundException{
        Connection conn = FabricaConexao.getConexao();
	String query = "SELECT publisher_id, name FROM PUBLISHERS;";
	PreparedStatement ps = conn.prepareStatement(query);
	ResultSet rs = ps.executeQuery();
		
	while(rs.next()){
           listNome_editora.add(rs.getString(2));
           listId_editora.add(rs.getString(1));
	}
	ps.close();
        listNome_editora.add(0, null);
        listId_editora.add(0, null);
    }
    
}
