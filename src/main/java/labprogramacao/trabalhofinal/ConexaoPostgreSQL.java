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
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author carol
 */
public class ConexaoPostgreSQL {
    private String usuario;
    private String senha;
    private String endereco;
    private Connection conexao;
    PreparedStatement comando;
    ResultSet resultado;
    JFrame frame = new JFrame("");

    public ConexaoPostgreSQL(String usuario, char[] senhaRecebida, String endereco) {
        this.endereco = endereco;
        this.usuario = usuario;
        this.senha = String.valueOf(senhaRecebida);
        
        try {
//            Class.forName("org.postgresql.Driver");
            DriverManager.registerDriver(new org.postgresql.Driver());
            conexao = DriverManager.getConnection(endereco, this.usuario, this.senha);
            
            JOptionPane.showMessageDialog(frame, "Conexão realizada com sucesso",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            
            String sql = "CREATE TABLE IF NOT EXISTS tarefa (id serial NOT NULL,descricao text NOT NULL, PRIMARY KEY (id))";
            PreparedStatement comando = conexao.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS);
            comando.execute();
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(frame, "Ocorreu um erro ao tentar se conectar com o banco de dados",
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public int adicionar(String sql) {
        try {
            PreparedStatement comando = conexao.prepareStatement(sql ,Statement.RETURN_GENERATED_KEYS);
            comando.execute();
            
            resultado = comando.getGeneratedKeys();
            
            if(resultado.next()){
                return resultado.getInt(1);
            } else {
                return -1;
            }
            
        } catch (Exception e) {
            return -1;
        }
    }
    
    public int deletar(int id) {
        String sql = "DELETE FROM tarefa WHERE ID=" + id;
        
        try {
            Statement comando = conexao.createStatement();
            int resposta = comando.executeUpdate(sql);
            
            return resposta;
        } catch (Exception e) {
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
