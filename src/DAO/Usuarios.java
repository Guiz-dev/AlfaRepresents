/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author guizinho
 */
public class Usuarios {
    public static boolean validaUsuario(String login, String senha) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement pst = null;
        
        ResultSet rs = null;
        boolean achou = false;
      
        try { 
            pst = con.prepareStatement("SELECT ID FROM USUARIOS WHERE LOGIN = ? AND SENHA = ?");
            pst.setString(1,login);
            pst.setString(2,senha);
            rs = pst.executeQuery();
            if (rs.next()) achou = true;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao selecionar.");
        } finally {
            ConnectionFactory.closeConnection(con, pst, rs);
            return achou;
        }
    }
}
