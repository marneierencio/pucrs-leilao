/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import negocio.pojos.Lance;

/**
 *
 * @author Marnei
 */
public interface LanceDAO {

    public Lance criar(Lance lance) throws DAOException;

    public Lance recuperar(Integer codigo) throws DAOException;

    public Lance atualizar(Lance lance) throws DAOException;

    public Lance remover(Lance lance) throws DAOException;

    public List<Lance> listar(Lance criterio) throws DAOException;

}
