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
    static LeilaoControlador controlador;
    
    static LeilaoControlador getInstance() throws GuiException, NegocioException, DAOException {
	if(controlador == null)
	    controlador = new LeilaoControlador();
	return controlador;
    }
    
    public LeilaoControlador() throws GuiException, NegocioException, DAOException {
        fachada = new LeilaoFachada();
    }
    
    public ParametrosConexao obterParametrosConexao() throws NegocioException, DAOException{
	ParametrosConexao pc = fachada.obterParametrosConexao();
	return pc;
    }

    public ParametrosConexao atualizarParametrosConexao(ParametrosConexao parametrosConexao) throws DAOException {
	return fachada.atualizarParametrosConexao(parametrosConexao);
    }
}
