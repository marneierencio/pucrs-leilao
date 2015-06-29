/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio.pojos;

import java.util.List;

/**
 *
 * @author Marnei
 */
public class Lote {

	private Integer codigo;
	private List<Bem> bens;
	private Leilao leilao;
	private Integer versao;

	public Lote() {
	}

	public Lote(Integer codigo, List<Bem> bens, Leilao leilao, Integer versao) {
		this.codigo = codigo;
		this.bens = bens;
		this.leilao = leilao;
		this.versao = versao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public List<Bem> getBens() {
		return bens;
	}

	public void setBens(List<Bem> bens) {
		this.bens = bens;
	}

	public void addBem(Bem bem) {
		this.bens.add(bem);
	}

	public void removeBem(Bem bem) {
		this.bens.remove(bem);
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
