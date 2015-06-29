/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.DAOException;
import dados.LeilaoDAODerby;
import java.util.List;
import negocio.pojos.Leilao;

/**
 *
 * @author Marnei
 */
public interface LeilaoDAO {

	public static LeilaoDAO getInstance() throws DAOException{
		return LeilaoDAODerby.getInstance();
	}
    public Leilao criar(Leilao leilao) throws DAOException;

    public Leilao recuperar(Integer codigo) throws DAOException;

    public Leilao atualizar(Leilao leilao) throws DAOException;

    public Leilao remover(Leilao leilao) throws DAOException;
    
    public List<Leilao> listar(Leilao criterio) throws DAOException;

	public List<Leilao> listarEmAndamento() throws DAOException;
}
