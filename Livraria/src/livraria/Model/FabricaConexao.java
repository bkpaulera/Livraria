/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package livraria.Model;

import java.sql.*;

/**
 *
 * @author thaismor
 */
public class FabricaConexao {
    private static final String USUARIO = "thaismor";
		  private static final String SENHA = "dqm10vnc";
		  private static final String DATABASE = "livraria";
		  private static final String STR_CONEXAO = "jdbc:postgresql://localhost:5432/";

		  public static Connection getConexao() throws SQLException, ClassNotFoundException {
		    Connection conn = null;
		    try {
		      conn = DriverManager.getConnection(STR_CONEXAO + DATABASE, USUARIO, SENHA);
		      return conn;
		    } catch (SQLException e) {
		      throw new SQLException("Erro ao conectar "
		              + "com a base de dados" + e.getMessage());
		    }
		  }

		  public static void fechaConexao(Connection conn) {
		    try {
		      if (conn != null) {
		        conn.close();
		        System.out.println("Fechada a conexão com o banco de dados");
		      }
		    } catch (Exception e) {
		      System.out.println("Não foi possível fechar a conexão com o banco de dados " + e.getMessage());
		    }
		  }

		  public static void fechaConexao(Connection conn, PreparedStatement stmt) {

		    try {
		      if (conn != null) {
		        fechaConexao(conn);
		      }
		      if (stmt != null) {
		        stmt.close();
		        System.out.println("Statement fechado com sucesso");
		      }
		    } catch (Exception e) {
		      System.out.println("Não foi possível fechar o statement " + e.getMessage());
		    }
		  }

		  public static void fechaConexao(Connection conn, PreparedStatement stmt, ResultSet rs) {

		    try {
		      if (conn != null || stmt != null) {
		        fechaConexao(conn, stmt);
		      }
		      if (rs != null) {
		        rs.close();
		        System.out.println("ResultSet fechado com sucesso");
		      }


		    } catch (Exception e) {
		      System.out.println("Não foi possível fechar o ResultSet " + e.getMessage());
		    }
		  }

}
