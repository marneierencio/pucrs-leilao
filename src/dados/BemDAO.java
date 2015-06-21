/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.util.List;
import negocio.pojos.Bem;

/**
 *
 * @author Marnei
 */
public interface BemDAO {

    public Bem criar(Bem bem) throws DAOException;

    public Bem recuperar(Integer codigo) throws DAOException;

    public Bem atualizar(Bem bem) throws DAOException;

    public Bem remover(Bem bem) throws DAOException;
    
    public List<Bem> listar(Bem criterio) throws DAOException;
}
