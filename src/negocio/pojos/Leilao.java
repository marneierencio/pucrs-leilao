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
public class Leilao {

    private Integer codigo;
    private Natureza natureza;
    private FormaLance formaLance;
    private Date dataHoraInicio;
    private Date dataHoraTermino;
    private Double preco;
    private Usuario usuario;
    private Lote lote;
    private Integer versao;

    public Leilao() {
    }

    public Leilao(Integer codigo, Natureza natureza, FormaLance formaLance, Date dataHoraInicio, Date dataHoraTermino, Double preco, Usuario usuario, Lote lote, Integer versao) {
        this.codigo = codigo;
        this.natureza = natureza;
        this.formaLance = formaLance;
        this.dataHoraInicio = dataHoraInicio;
        this.dataHoraTermino = dataHoraTermino;
        this.preco = preco;
        this.usuario = usuario;
        this.lote = lote;
        this.versao = versao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Natureza getNatureza() {
        return natureza;
    }

    public void setNatureza(Natureza natureza) {
        this.natureza = natureza;
    }

    public FormaLance getFormaLance() {
        return formaLance;
    }

    public void setFormaLance(FormaLance formaLance) {
        this.formaLance = formaLance;
    }

    public Date getDataHoraInicio() {
        return dataHoraInicio;
    }

    public void setDataHoraInicio(Date dataHoraInicio) {
        this.dataHoraInicio = dataHoraInicio;
    }

    public Date getDataHoraTermino() {
        return dataHoraTermino;
    }

    public void setDataHoraTermino(Date dataHoraTermino) {
        this.dataHoraTermino = dataHoraTermino;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }
    
    
}
