package br.edu.ifsul.dao;

import java.io.Serializable;

public class Ordem implements Serializable {
    
    private String atributo;
    private String label;
    private String operador;

    /**
     * 
     * @param atributo Atributo da classe a ser usado no filtro e ordenação
     * @param label Valor a ser exibido para o usuário na tela
     * @param operador Operador usado na consulta =, like, <, >
     */
    public Ordem(String atributo, String label, String operador) {
        this.atributo = atributo;
        this.label = label;
        this.operador = operador;
    }

    @Override
    public String toString() {
        return label;
    }
    
    

    public String getAtributo() {
        return atributo;
    }

    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }
}