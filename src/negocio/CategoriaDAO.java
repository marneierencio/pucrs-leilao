/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.CategoriaDAODerby;
import dados.DAOException;
import java.util.List;
import negocio.pojos.Categoria;

/**
 *
 * @author Marnei
 */
public interface CategoriaDAO {

	public static CategoriaDAO getInstance() throws DAOException {
		return CategoriaDAODerby.getInstance();
	}

	public Categoria criar(Categoria categoria) throws DAOException;

	public Categoria recuperar(Integer codigo) throws DAOException;

	public Categoria atualizar(Categoria categoria) throws DAOException;

	public Categoria remover(Categoria categoria) throws DAOException;

	public List<Categoria> listar(Categoria criterio) throws DAOException;
}
