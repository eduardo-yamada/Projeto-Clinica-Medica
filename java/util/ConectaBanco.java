/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author felip
 */
public class ConectaBanco {
    
    
    public static Connection getConexao(){
        try{
            
             Class.forName("org.postgresql.Driver");
                                                   //local do banco                              //usuario  //senha
             return   DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbclinica", "postgres", "postgres"); //retorna um Connection
            
        }catch(Exception e){
            throw new RuntimeException(e);
        }
       
        
    }
}