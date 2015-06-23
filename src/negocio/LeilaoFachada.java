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
//    public LeilaoFachada getInstance() throws NegocioException, DAOException{
//	if(fachada == null){
//	    fachada = new LeilaoFachada();
//	}
//	return fachada;
//    }
    
    public ParametrosConexao obterParametrosConexao() throws NegocioException{
	ParametrosConexao pc = null;
	try {
	    pc = pcDAO.recuperar();
	} catch (DAOException ex) {
	    Logger.getLogger(LeilaoFachada.class.getName()).log(Level.SEVERE, null, ex);
	}
	//return fachada.obterParametrosConexao();
	return pc;
    }
}
