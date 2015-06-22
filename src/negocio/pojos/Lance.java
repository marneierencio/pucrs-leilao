/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.pojos;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Marnei
 */
public class Lance {

    private Integer codigo;
    private Date data;
    private Time hora;
    private Double valor;
    private Usuario usuario;
    private Leilao leilao;
    private Integer versao;

    public Lance() {
    }

    public Lance(Integer codigo, Date data, Time hora, Double valor, Usuario usuario, Leilao leilao, Integer versao) {
        this.codigo = codigo;
        this.data = data;
        this.hora = hora;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
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
