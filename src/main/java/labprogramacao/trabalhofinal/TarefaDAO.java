/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labprogramacao.trabalhofinal;

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
    
    public void adicionar(String descricaoTarefa) {

        String consulta = "INSERT INTO tarefa (id, descricao)" + 
                "VALUES (default," + descricaoTarefa + ")\"";
        
        int resposta = conexao.executaSQL(consulta);
    }
    
    public void remover(int id) {

        String consulta = "DELETE FROM tarefa WHERE ID= #{ id } ";
        
        int resposta = conexao.executaSQL(consulta);
    }
       
}
