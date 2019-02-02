/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author guizinho
 */
public class ConnectionFactory {
  private static final String DRIVER = "com.mysql.jdbc.Driver";
  private static final String URL = "jdbc:mysql://localhost:3306/alfa";
  private static final String USER = "root";
  private static final String PASS = "";
  
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException ("Erro na conexão: ", ex);
        }
    }
  
  public static void closeConnection(Connection con) {
    try {
      if (con != null) con.close();
    } catch (SQLException e) {
      throw new RuntimeException("Erro no fechamento da conexão");
    }
  }
  
  public static void closeConnection(Connection con, PreparedStatement pst) {
    try {
      if (pst != null) pst.close();
      closeConnection(con);
    } catch (SQLException e) {
      throw new RuntimeException("Erro no fechamento do statement");
    }
  }
  
  public static void closeConnection(Connection con, PreparedStatement pst, ResultSet rs) {
    try {
      if (rs != null) rs.close();
      closeConnection(con,pst);
    } catch (SQLException e) {
      throw new RuntimeException("Erro no fechamento do result set");
    }
  }
  
}
