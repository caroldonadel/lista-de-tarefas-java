/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labprogramacao.trabalhofinal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author carol
 */
public class ConexaoPostgreSQL {
    private String url;
    private String usuario;
    private String senha;
    private Connection conexao;

    public ConexaoPostgreSQL() {
        this.url = "jdbc:postgresql://localhost:5432/postgres";
        this.usuario = "postgres";
        this.senha = "1234";
        this.conexao = conexao;
        
        try {
            Class.forName("org.postgresel.Driver");
            conexao = DriverManager.getConnection(url, usuario, senha);
            
            System.out.println("Conexão realizada com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int executaSQL(String sql) {
        PreparedStatement prep;
        ResultSet result;
        
        try {
            Statement comando = conexao.createStatement();
            prep = conexao.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS);
            int resposta = comando.executeUpdate(sql);
            
            result = prep.getGeneratedKeys();
            conexao.close();
            
            if(result.next() && result != null){
                return result.getInt(1);
            } else {
                return -1;
            }
            
        } catch (Exception e) {
            return -1;
        }
    }
}
