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
public class Categoria {

    private Integer codigo;
    private String descricao;
    private Integer versao;

    public Categoria() {
    }

    public Categoria(Integer codigo, String descricao, Integer versao) {
        this.codigo = codigo;
        this.descricao = descricao;
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

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }
    
    
}
