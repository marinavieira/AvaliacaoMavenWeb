/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.converters.ConverterOrdem;
import br.edu.ifsul.modelo.Prova;
import java.io.Serializable;
import java.util.List;
import javax.ejb.Stateful;

/**
 *
 * @author marina.ferreira
 */
@Stateful
public class ProvaDAO<TIPO> extends DAOGenerico<Prova> implements Serializable {

    public ProvaDAO() {
        super();
        classePersistente = Prova.class;
        listaOrdem.add(new Ordem("id", "ID", "="));
        listaOrdem.add(new Ordem("descricao", "Descrição", "like"));
        listaOrdem.add(new Ordem("professor", "Professor", "like"));
        // definir qual a ordenação padrão no caso o segundo elemento da lista (indice 1)
        ordemAtual = listaOrdem.get(0);
        // inicializar o conversor das ordens
        converterOrdem = new ConverterOrdem();
        converterOrdem.setListaOrdem(listaOrdem);  
    }

        @Override
    public Prova getObjectByID(Object id) throws Exception {
        Prova obj = em.find(Prova.class, id);
        // uso para evitar o erro de lazy inicialization exception
       obj.getNotas().size();
        return obj;
    }    
    
    public List<Prova> getListaObjetosCompleta(){
        String jpql = "select distinct p from Prova p left join fetch p.notas order by p.id";
        return em.createQuery(jpql).getResultList();
    }
}