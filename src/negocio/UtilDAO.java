/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.DAOException;
import dados.UtilDAODerby;

/**
 *
 * @author Marnei
 */
public interface UtilDAO {
    public static UtilDAO getInstance() throws DAOException{
	return UtilDAODerby.getInstance();
    }
    public Boolean criarBancoDados() throws DAOException;
    public Boolean recriarBancoDados() throws DAOException;
    public Boolean popularBancoDados() throws DAOException;
}
