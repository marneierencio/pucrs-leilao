/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.DAOException;
import java.util.List;
import negocio.pojos.Usuario;

/**
 *
 * @author Marnei
 */
public interface UsuarioDAO {

    public Usuario criar(Usuario Usuario) throws DAOException;

    public Usuario recuperar(Integer codigo) throws DAOException;

    public Usuario atualizar(Usuario usuario) throws DAOException;

    public Usuario remover(Usuario usuario) throws DAOException;

    public List<Usuario> listar(Usuario criterio) throws DAOException;
}
