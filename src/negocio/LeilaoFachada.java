/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.DAOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class LeilaoFachada {

	ParametrosConexaoDAO pcDAO;
	UtilDAO utilDAO;

	BemDAO cadBens;
	CategoriaDAO cadCategorias;
	LanceDAO cadLances;
	LeilaoDAO cadLeiloes;
	UsuarioDAO cadUsuarios;

	public LeilaoFachada() throws NegocioException, DAOException {
		pcDAO = ParametrosConexaoDAO.getInstance();
		utilDAO = UtilDAO.getInstance();
		cadBens = BemDAO.getInstance();
		cadCategorias = CategoriaDAO.getInstance();
		cadLances = LanceDAO.getInstance();
		cadLeiloes = LeilaoDAO.getInstance();
		cadUsuarios = UsuarioDAO.getInstance();
	}

	public ParametrosConexao obterParametrosConexao() throws NegocioException, DAOException {
		return pcDAO.recuperar();
	}

	public ParametrosConexao atualizarParametrosConexao(ParametrosConexao parametrosConexao) throws DAOException {
		return pcDAO.atualizar(parametrosConexao);
	}

	public boolean criarBD() throws DAOException {
		return UtilDAO.getInstance().criarBancoDados();
	}

	public boolean recriarBD() throws DAOException {
		return UtilDAO.getInstance().recriarBancoDados();
	}

	public boolean popularBD() throws DAOException {
		return UtilDAO.getInstance().popularBancoDados();
	}

	public Usuario logarUsuario(String email, String senha) throws DAOException {
		Usuario u = new Usuario();
		u.setEmail(email);
		try {
			u = cadUsuarios.listar(u).get(0);
			if(u.getSenha().equals(senha)){
				return u;
			}
		} catch (IndexOutOfBoundsException ex){
			u = null;
		}
		return null;
	}

	public Usuario salvarUsuario(Usuario usuario) throws DAOException {
		if(usuario.getCodigo() == null)
			if(cadUsuarios.listar(new Usuario(null, null, null, usuario.getEmail(), null, null)).isEmpty())
				usuario = cadUsuarios.criar(usuario);
			else
				throw new DAOException("Este e-mail já foi cadastrado."); 
		else
			usuario = cadUsuarios.atualizar(usuario);
		return usuario;
	}

	public List<Leilao> listarLeiloesDoUsuario(Usuario usuario) throws DAOException {
		Leilao criterio = new Leilao(null, null, null, null, null, null, null, null, usuario, null);
		List<Leilao> leiloes = cadLeiloes.listar(criterio);
		return leiloes;
	}

	public Categoria salvarCategoria(Categoria cat) throws DAOException {
		if(cat.getCodigo() != null){
			return cadCategorias.atualizar(cat);
		}
		else
		{
			return cadCategorias.criar(cat);
		}
	}
	
	public List<Categoria> listarCategorias(Categoria criterio) throws DAOException{
		return cadCategorias.listar(criterio);
	}

}
