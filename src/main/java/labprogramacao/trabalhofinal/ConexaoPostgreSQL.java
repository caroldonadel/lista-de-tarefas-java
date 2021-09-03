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
import java.util.ArrayList;

/**
 *
 * @author carol
 */
public class ConexaoPostgreSQL {
    private String url;
    private String usuario;
    private String senha;
    private Connection conexao;
    
    PreparedStatement comando;
    ResultSet result;

    public ConexaoPostgreSQL() {
        this.url = "jdbc:postgresql://localhost:5432/postgres";
        this.usuario = "postgres";
        this.senha = "1234";
        this.conexao = conexao;
        
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            conexao = DriverManager.getConnection(url, usuario, senha);
            
            System.out.println("Conexão realizada com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int adicionar(String sql) {
        try {
            PreparedStatement comando = conexao.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS);
            comando.execute();
            
            result = comando.getGeneratedKeys();
            
            if(result.next()){
                return result.getInt(1);
            } else {
                return -1;
            }
            
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }
    
    public int deletar(int id) {
        
        String sql = "DELETE FROM tarefa WHERE ID=" + id;
        
        try {
            Statement comando = conexao.createStatement();
            int resposta = comando.executeUpdate(sql);
            
            System.out.println(resposta);
            return resposta;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }
    
    public int deletarTodos() {
        
        String sql = "DELETE FROM tarefa";
        
        try {
            Statement comando = conexao.createStatement();
            int resposta = comando.executeUpdate(sql);
            
            return resposta;
        } catch (Exception e) {
            System.out.println(e);
            return -1;
        }
    }
    
    public ArrayList<Tarefa> busca() {
        String sql = "SELECT * FROM tarefa";
        Tarefa tarefa = null;
        ArrayList<Tarefa> tarefas = new ArrayList();
        
        try {
            Statement comando = conexao.createStatement();
            ResultSet resposta = comando.executeQuery(sql);
            
            while (resposta.next()) {
                tarefa = new Tarefa(resposta.getString(2), resposta.getInt(1));
                tarefas.add(tarefa);
            }
            
            return tarefas;
            
        } catch (Exception e) {
            return null;
        }
    }
}
