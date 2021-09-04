/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labprogramacao.trabalhofinal;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author carol
 */
public class TarefaDAO {

    private static final TarefaDAO instance = new TarefaDAO();
    private ConexaoPostgreSQL conexao = null;
    private String usuario;
    private String endereco;
    private char[] senha;

    public static TarefaDAO getInstance() {
        return instance;
    }
    
    public void conectar() {
        this.conexao = new ConexaoPostgreSQL(this.usuario, this.senha, this.endereco);
    }
    
    public int adicionar(String descricaoTarefa) {
        
        String consulta = "INSERT INTO tarefa VALUES ( default," + "'" + descricaoTarefa + "'" + ")";
        
        int resposta = conexao.adicionar(consulta);
        
        return resposta;
    }
    
    public int remover(int id) {
        int resposta = conexao.deletar(id);
        return resposta;
    }
    
    public int removerTodos() {
        return conexao.deletarTodos();
    }
    
    public ArrayList<Tarefa> buscar() {
        
        return conexao.busca();
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setSenha(char[] senha) {
        this.senha = senha;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
