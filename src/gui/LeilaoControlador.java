/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dados.DAOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.LeilaoFachada;
import negocio.NegocioException;
import negocio.pojos.ParametrosConexao;

/**
 *
 * @author Marnei
 */
class LeilaoControlador {
    LeilaoFachada fachada;
    
    public LeilaoControlador() throws GuiException, NegocioException, DAOException {
        fachada = new LeilaoFachada();
    }
    
    public ParametrosConexao obterParametrosConexao(){
	ParametrosConexao pc = null;
	try {
	    pc = fachada.obterParametrosConexao();
	} catch (NegocioException ex) {
	    Logger.getLogger(LeilaoControlador.class.getName()).log(Level.SEVERE, null, ex);
	}
	return pc;
    }

    
}
