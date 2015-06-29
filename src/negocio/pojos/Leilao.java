/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.pojos;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Marnei
 */
public class Leilao {

    private Integer codigo;
    private Natureza natureza;
    private FormaLance formaLance;
    private Date dataInicio;
    private Time horaInicio;
    private Date dataTermino;
    private Time horaTermino;
    private Double preco;
    private Usuario usuario;
    private Lote lote;
    private List<Lance> lances;
    private Integer versao;

    public Leilao() {
    }

    public Leilao(Integer codigo, Natureza natureza, FormaLance formaLance, Date dataInicio, Time horaInicio, Date dataTermino, Time horaTermino, Double preco, Usuario usuario, Integer versao) {
        this.codigo = codigo;
        this.natureza = natureza;
        this.formaLance = formaLance;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
        this.dataTermino = dataTermino;
        this.horaTermino = horaTermino;
        this.preco = preco;
        this.usuario = usuario;
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

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Time getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Time horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public Time getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(Time horaTermino) {
        this.horaTermino = horaTermino;
    }

    public List<Lance> getLances() {
        return lances;
    }

    public void setLances(List<Lance> lances) {
        this.lances = lances;
    }

    public void addLance(Lance lance) {
        this.lances.add(lance);
    }
	
    public void removeLance(Lance lance) {
        this.lances.remove(lance);
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
