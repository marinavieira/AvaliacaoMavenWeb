/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Nota;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author marina.ferreira
 */
@Stateful
public class NotaDAO<TIPO> extends DAOGenerico<Nota> implements Serializable {

    public NotaDAO() {
        super();
        classePersistente = Nota.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
        ordemAtual = listaOrdem.get(1);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);  
    }

}