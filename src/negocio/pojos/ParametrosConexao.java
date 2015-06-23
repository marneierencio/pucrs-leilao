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
public class ParametrosConexao {

    private String bdServidor;
    private Integer bdPorta;
    private String bdNome;
    private String bdUsuario;
    private String bdSenha;

    public ParametrosConexao() {
    }

    public String getBdServidor() {
	return bdServidor;
    }

    public void setBdServidor(String bdServidor) {
	this.bdServidor = bdServidor;
    }

    public Integer getBdPorta() {
	return bdPorta;
    }

    public void setBdPorta(Integer bdPorta) {
	this.bdPorta = bdPorta;
    }
    
    public String getBdNome() {
	return bdNome;
    }

    public void setBdNome(String bdNome) {
	this.bdNome = bdNome;
    }

    public String getBdUsuario() {
	return bdUsuario;
    }

    public void setBdUsuario(String bdUsuario) {
	this.bdUsuario = bdUsuario;
    }

    public String getBdSenha() {
	return bdSenha;
    }

    public void setBdSenha(String bdSenha) {
	this.bdSenha = bdSenha;
    }
    
    
    
}
