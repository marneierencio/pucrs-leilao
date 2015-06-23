/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.DAOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.pojos.ParametrosConexao;

/**
 *
 * @author Marnei
 */
public class LeilaoFachada {
    ParametrosConexaoDAO pcDAO;
    
    public LeilaoFachada() throws NegocioException, DAOException{
	try{
	    pcDAO = ParametrosConexaoDAO.getInstance();
	} catch (DAOException e) {
            throw new DAOException("Falha de criação da fachada!", e);
        }
    }
    
    public ParametrosConexao obterParametrosConexao() throws NegocioException, DAOException{
	ParametrosConexao pc = pcDAO.recuperar();
	return pc;
    }
    
    public ParametrosConexao atualizarParametrosConexao(ParametrosConexao parametrosConexao) throws DAOException{
	return pcDAO.atualizar(parametrosConexao);
    }
}
