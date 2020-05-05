/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.medicoMOD;
import util.ConectaBanco;

/**
 *
 * @author Felipe
 */
public class medicoDAO {
        
    public boolean cadastrar(medicoMOD obj) throws SQLException {
        //os dados estão atribuidos aos atributos do objeto que foi passado para o método em questão
        // desta forma devemos criar um preparação para o comando SQL
        Connection conexao = null;

        conexao = ConectaBanco.getConexao();
        PreparedStatement pst = null;
        //Atomicidade
        try {

            conexao.setAutoCommit(false);

            String stm = "INSERT INTO public.medicos(nome, cpf, email, especialidade) VALUES (?, ?, ?, ?);";
            pst = conexao.prepareStatement(stm, Statement.RETURN_GENERATED_KEYS); //prepara para receber o ultimo id gerado

            pst.setString(1, obj.getNome());
            pst.setString(2, obj.getCpf());
            pst.setString(3, obj.getEmail());
            pst.setString(4, obj.getEspecialidade());
            
            pst.executeUpdate();

            //quando vai gravar a pessoa, já pede para retornar o ultimo ID
            try (ResultSet rsPessoa = pst.getGeneratedKeys()) {
                if (rsPessoa.next()) {
                    obj.setId((int) rsPessoa.getLong(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
            
           
            //insere a partir do id da pessoa o cliente respectivo
            
            stm = "INSERT INTO public.medicos(id) VALUES (?);";
             
            pst = conexao.prepareStatement(stm, Statement.RETURN_GENERATED_KEYS); //prepara para receber o ultimo id gerado

           
            pst.setInt(1, obj.getId());
          
            pst.executeUpdate();
            

            conexao.commit();

            return true;

        } catch (Exception e) {

            conexao.rollback();

            return false;
        }
    }
}
