/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifsul.controle;

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
@Named(value = "controleProva")
@ViewScoped
public class ControleProva implements Serializable {

    @EJB
    private ProvaDAO<Prova> dao;
    private Prova objeto;
    private Nota nota;
    private Boolean novaNota;
    private int abaAtiva;

    public ControleProva() {

    }

    public void novaNota() {
        nota = new Nota();
        novaNota = true;
    }

    public void alterarNota(int index) {
        nota = objeto.getNotas().get(index);
        novaNota = false;
    }

    public void salvarNota() {
        if (novaNota) {
            if (nota.getNota() >= 0 && nota.getNota() <= 10) {
                objeto.adicionarNota(nota);
                Util.mensagemInformacao("Nota adicionado ou alterado com sucesso!");
            } else {
                Util.mensagemErro("A nota esta fora do intervalo permitido.");
            }
        }
        
    }

    public void removerNota(int index) {
        objeto.removerNota(index);
        Util.mensagemInformacao("Nota removido com sucesso!");
    }

    public String listar() {
        return "/privado/prova/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Prova();
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

    public ProvaDAO<Prova> getDao() {
        return dao;
    }

    public void setDao(ProvaDAO<Prova> dao) {
        this.dao = dao;
    }

    public Prova getObjeto() {
        return objeto;
    }

    public void setObjeto(Prova objeto) {
        this.objeto = objeto;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public Boolean getNovaNota() {
        return novaNota;
    }

    public void setNovaNota(Boolean novaNota) {
        this.novaNota = novaNota;
    }

    public int getAbaAtiva() {
        return abaAtiva;
    }

    public void setAbaAtiva(int abaAtiva) {
        this.abaAtiva = abaAtiva;
    }

}
