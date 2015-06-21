/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.pojos;

import java.util.Date;

/**
 *
 * @author Marnei
 */
public class Lance {

    private Integer codigo;
    private Date dataHora;
    private Double valor;
    private Usuario usuario;
    private Leilao leilao;
    private Integer versao;

    public Lance() {
    }

    public Lance(Integer codigo, Date dataHora, Double valor, Usuario usuario, Leilao leilao, Integer versao) {
        this.codigo = codigo;
        this.dataHora = dataHora;
        this.valor = valor;
        this.usuario = usuario;
        this.leilao = leilao;
        this.versao = versao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Leilao getLeilao() {
        return leilao;
    }

    public void setLeilao(Leilao leilao) {
        this.leilao = leilao;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }
    
    
}
