/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.DAOException;
import java.util.List;
import negocio.pojos.Lote;

/**
 *
 * @author Marnei
 */
public interface LoteDAO {

    public Lote criar(Lote lote) throws DAOException;

    public Lote recuperar(Integer codigo) throws DAOException;

    public Lote atualizar(Lote lote) throws DAOException;

    public Lote remover(Lote lote) throws DAOException;

    public List<Lote> listar(Lote criterio) throws DAOException;
}
