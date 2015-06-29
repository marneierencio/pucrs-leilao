/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.pojos;

/**
 *
 * @author Marnei
 */
public class Bem{
    private Integer codigo;
    private String descricao;
    private String descricaoDetalhada;
    private Lote lote;
    private Categoria categoria;
    private Integer versao;

    public Bem() {
    }

    public Bem(Integer codigo, String descricao, String descricaoDetalhada, Lote lote, Categoria categoria, Integer versao) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.descricaoDetalhada = descricaoDetalhada;
	this.lote = lote;
        this.categoria = categoria;
        this.versao = versao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoDetalhada() {
        return descricaoDetalhada;
    }

    public void setDescricaoDetalhada(String descricaoDetalhada) {
        this.descricaoDetalhada = descricaoDetalhada;
    }

    public Lote getLote() {
	return lote;
    }

    public void setLote(Lote lote) {
	this.lote = lote;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }
    
}
