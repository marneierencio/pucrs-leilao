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
public class Usuario {

    private Integer codigo;
    private String cpfCnpj;
    private String nome;
    private String email;
    private String senha;
    private Integer versao;

    public Usuario() {
    }

    public Usuario(Integer codigo, String cpfCnpj, String nome, String email, String senha, Integer versao) {
        this.codigo = codigo;
        this.cpfCnpj = cpfCnpj;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.versao = versao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }
    
    
}
