/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import model.bean.CadRepre;

/**
 *
 * @author guizinho
 */
public class CadRepreDAO {
    
    public void create(CadRepre r){
        
        Connection con = ConnectionFactory.getConnection(); //abre a conecção com o BD.
        PreparedStatement stmt = null; //Prepara o Conexão com o BD.
        
        try {
            stmt = con.prepareStatement("INSERT INTO representantes values (default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
            stmt.setString(1, r.getRazao());
            stmt.setString(2, r.getData_nasc()); //CAMPO FORMATADO
            stmt.setString(3, r.getRg()); //CAMPO FORMATADO
            stmt.setString(4, r.getData_adm());
            stmt.setString(5, r.getCodigo());
            stmt.setString(6, r.getEndereco());
            stmt.setString(7, r.getCidade());
            stmt.setString(8, r.getUf());
            stmt.setString(9, r.getBairro());
            stmt.setString(10, r.getCep());
            stmt.setString(11, r.getComplemento());
            stmt.setString(12, r.getCel1());
            stmt.setString(13, r.getCel2());
            stmt.setString(14, r.getTel_fixo());
            stmt.setString(15, r.getFax());
            stmt.setString(16, r.getEmail());
            stmt.setString(17, r.getTime());
            stmt.setString(18, r.getEstado_civil());
            stmt.setString(19, r.getFilhos());
            stmt.setString(20, r.getCarro());
            stmt.setString(21, r.getBeneficiario());
            stmt.setString(22, r.getAgencia());
            stmt.setString(23, r.getConta());
            stmt.setString(24, r.getBanco());
            
            stmt.executeUpdate(); //Executa e insere no BD.
            JOptionPane.showMessageDialog(null, "Salvo com suceso!");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar!"+ex);
            
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    /**
     *
     * @return
     */
    public List<CadRepre> read(){
        
        Connection con = ConnectionFactory.getConnection(); //abre a conexão com o BD.
        PreparedStatement stmt = null; //Prepara o Conexão com o BD.
        
        ResultSet rs = null;
        
        List<CadRepre> representantes = new ArrayList<>(); // Os valores vindos do BD abaixo será inserido dentro desta lista.
        
        try {
            stmt = con.prepareStatement("SELECT * FROM REPRESENTANTES");
            rs = stmt.executeQuery(); //Executa e consulta no BD.
            
            while(rs.next()){ //enquanto houver resultados ele vai verificar o próximo
                CadRepre repre = new CadRepre();
                
                repre.setRazao(rs.getString("razao")); //em laranja a coluna do banco
                repre.setData_nasc(rs.getString("data_nasc"));
                repre.setRg(rs.getString("rg"));
                repre.setData_adm(rs.getString("data_adm"));
                repre.add(representantes);
            }
                    } catch (SQLException ex) {
            throw new RuntimeException ("Erro na conexão: ", ex);
        }  finally{
            ConnectionFactory.closeConnection(con, stmt, rs); //fechar as conexões.
        }
   return representantes;

    } 
    
}
