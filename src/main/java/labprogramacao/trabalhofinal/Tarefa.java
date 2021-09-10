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
public class Tarefa {
    private String descricao;
    private int id;

    public Tarefa(String descricao, int id) {
        this.descricao = descricao;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}
