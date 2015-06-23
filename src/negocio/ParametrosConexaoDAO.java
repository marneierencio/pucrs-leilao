/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.DAOException;
import dados.ParametrosConexaoDAOArquivo;
import negocio.pojos.ParametrosConexao;

/**
 *
 * @author Marnei
 */
public interface ParametrosConexaoDAO {
    public static ParametrosConexaoDAO getInstance() throws DAOException{
	return ParametrosConexaoDAOArquivo.getInstance();
    }

    public ParametrosConexao recuperar() throws DAOException;

    public ParametrosConexao atualizar(ParametrosConexao parametrosConexao) throws DAOException;
}
