/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labprogramacao.trabalhofinal;

import java.util.ArrayList;

/**
 *
 * @author carol
 */
public class TarefaDAO {

    private static final TarefaDAO instance = new TarefaDAO();
    private ConexaoPostgreSQL conexao = new ConexaoPostgreSQL();

    public static TarefaDAO getInstance() {
            return instance;
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
    
    public ArrayList<Tarefa> buscar() {

        return conexao.busca();
    }
}
