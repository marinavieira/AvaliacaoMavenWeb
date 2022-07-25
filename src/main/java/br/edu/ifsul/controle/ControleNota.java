/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.NotaDAO;
import br.edu.ifsul.dao.ProvaDAO;
import br.edu.ifsul.modelo.Nota;
import br.edu.ifsul.modelo.Prova;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author marina.ferreira
 */
@Named(value = "controleNota")
@ViewScoped
public class ControleNota implements Serializable {

    @EJB
    private NotaDAO<Nota> dao;
    private Nota objeto;
    @EJB
    private ProvaDAO<Prova> daoProva;

    public ControleNota() {

    }

    public String listar() {
        return "/privado/nota/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Nota();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectByID(id);
        } catch (Exception e) {
            Util.mensagemInformacao("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectByID(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public NotaDAO<Nota> getDao() {
        return dao;
    }

    public void setDao(NotaDAO<Nota> dao) {
        this.dao = dao;
    }

    public Nota getObjeto() {
        return objeto;
    }

    public void setObjeto(Nota objeto) {
        this.objeto = objeto;
    }

    public ProvaDAO<Prova> getDaoProva() {
        return daoProva;
    }

    public void setDaoProva(ProvaDAO<Prova> daoProva) {
        this.daoProva = daoProva;
    }

}
