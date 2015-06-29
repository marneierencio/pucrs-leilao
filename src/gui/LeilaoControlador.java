/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dados.DAOException;
import java.util.List;
import negocio.LeilaoFachada;
import negocio.NegocioException;
import negocio.pojos.Categoria;
import negocio.pojos.Leilao;
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

    /**************
     PARÂMETROS DE CONEXÃO
     */
    public ParametrosConexao obterParametrosConexao() throws DAOException, NegocioException {
        return fachada.obterParametrosConexao();
    }

    public ParametrosConexao atualizarParametrosConexao(ParametrosConexao parametrosConexao) throws DAOException {
        return fachada.atualizarParametrosConexao(parametrosConexao);
    }

    /**************
     BANCO DE DADOS
     */
    public boolean criarBD() throws DAOException {
        return fachada.criarBD();
    }

    public boolean recriarBD() throws DAOException {
        return fachada.recriarBD();
    }

    public boolean popularBD() throws DAOException {
        return fachada.popularBD();
    }

    /**********
     CATEGORIAS
     */
    public List<Categoria> listarCategorias(Categoria criterio) throws DAOException {
        return fachada.listarCategorias(criterio);
    }

    /*******
     LEILÕES
     */
    public List<Leilao> listarLeiloesEmAndamento() throws DAOException {
        return fachada.listarLeiloesEmAndamento();
    }

    public List<Leilao> listarMeusLeiloes() throws DAOException {
         return fachada.listarLeiloesDoUsuario(usuarioLogado);
    }

    public Leilao salvarLeilao(Leilao leilao) throws DAOException {
        return fachada.salvarLeilao(leilao);
    }

    /**********
     USUÁRIOS
     */
    public Usuario logarUsuario(String email, String senha) throws DAOException {
        usuarioLogado = fachada.logarUsuario(email, senha);
        return usuarioLogado;
    }

    public Usuario obterUsuarioLogado() {
        return usuarioLogado;
    }

    public Usuario salvarUsuario(Usuario usuario) throws DAOException {
        return fachada.salvarUsuario(usuario);
    }
}
