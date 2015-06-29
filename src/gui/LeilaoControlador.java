/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dados.DAOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.LeilaoFachada;
import negocio.NegocioException;
import negocio.pojos.Categoria;
import negocio.pojos.FormaLance;
import negocio.pojos.Leilao;
import negocio.pojos.Natureza;
import negocio.pojos.ParametrosConexao;
import negocio.pojos.Usuario;

/**
 *
 * @author Marnei
 */
class LeilaoControlador {

	LeilaoFachada fachada;
	static LeilaoControlador controlador;
	Usuario usuarioLogado;

	static LeilaoControlador getInstance() throws GuiException, DAOException, NegocioException {
		if (controlador == null) {
			controlador = new LeilaoControlador();
		}
		return controlador;
	}

	public LeilaoControlador() throws GuiException, DAOException, NegocioException {
			fachada = new LeilaoFachada();
	}

	public ParametrosConexao obterParametrosConexao() throws DAOException, NegocioException {
		return fachada.obterParametrosConexao();
	}

	public ParametrosConexao atualizarParametrosConexao(ParametrosConexao parametrosConexao) throws DAOException {
		return fachada.atualizarParametrosConexao(parametrosConexao);
	}

	public boolean criarBD() throws DAOException {
		return fachada.criarBD();
	}

	public boolean recriarBD() throws DAOException {
		return fachada.recriarBD();
	}

	public boolean popularBD() throws DAOException {
		return fachada.popularBD();
	}

	public Usuario logarUsuario(String email, String senha) throws DAOException {
		usuarioLogado = fachada.logarUsuario(email, senha);
		return usuarioLogado;
	}

	public Usuario salvarUsuario(Usuario usuario) throws DAOException {
		return fachada.salvarUsuario(usuario);
	}

	public Usuario obterUsuarioLogado() {
		return usuarioLogado;
	}
	
	public List<Leilao> listarLeiloesEmAndamento() throws DAOException {
		
		return fachada.listarLeiloesEmAndamento();
	}
	
	public void listarMeusLeiloes() throws DAOException{
		List<Leilao> leiloes = fachada.listarLeiloesDoUsuario(usuarioLogado);
		//new JFrameListarLeiloes(leiloes).setVisible(true);
	}
	
	public List<Categoria> listarCategorias(Categoria criterio) throws DAOException{
		return fachada.listarCategorias(criterio);
	}
}
