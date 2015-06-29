/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import negocio.pojos.ParametrosConexao;
import org.apache.derby.jdbc.ClientDataSource;

/**
 *
 * @author Marnei
 * @param <T>
 */
public abstract class DAO<T> {
    //private static final ClientDataSource DATA_SOURCE = inicializarClientDataSource();
    //private static final DataSource DATA_SOURCE = inicializarDataSource();
    private static ClientDataSource DATA_SOURCE;
    protected Connection conexao;
    protected PreparedStatement stmt;

    public DAO() throws DAOException {
	DATA_SOURCE = inicializarClientDataSource();
	//private static final DataSource DATA_SOURCE = inicializarDataSource();

    }

//    private static DataSource inicializarDataSource() {
//	try {
//	    Context contexto = new InitialContext();
//	    Context envContexto = (Context) contexto.lookup("java:comp/env");
//
//	    DataSource dataSource = (DataSource) envContexto.lookup("jdbc/TrabalhoFinal");
//
//	    return dataSource;
//	} catch (NamingException e) {
//	    throw new Error("Erro ao inicializar o datasource. Causa: "
//		    + e.getMessage(), e);
//	}
//    }

    private static ClientDataSource inicializarClientDataSource(){
	//ClientDataSource dataSource = DATA_SOURCE;
	ClientDataSource dataSource = null;
	    ParametrosConexao parametrosConexao = null;
	if (DATA_SOURCE == null) {
	    try {
		parametrosConexao = new ParametrosConexaoDAOArquivo().recuperar();
	    } catch (DAOException ex) {
		Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
	    }
	    dataSource = new ClientDataSource();
	    dataSource.setServerName(parametrosConexao.getBdServidor());
	    dataSource.setPortNumber(parametrosConexao.getBdPorta());
	    dataSource.setDatabaseName(parametrosConexao.getBdNome());
	    dataSource.setUser(parametrosConexao.getBdUsuario());
	    dataSource.setPassword(parametrosConexao.getBdSenha());
	}
	else
	    dataSource = DATA_SOURCE;
	return dataSource;
    }

    public final void abrirConexao() throws DAOException {
	try {
	    if (conexao == null || conexao.isClosed()) {
		conexao = DATA_SOURCE.getConnection();
	    }
	} catch (Exception e) {
	    throw new DAOException("Erro ao abrir a conexão. Causa: "
		    + e.getMessage(), e);
	}
    }

    public final void fecharConexao() throws DAOException {
	if (conexao != null) {
	    try {
		if (!conexao.getAutoCommit()) {
		    conexao.commit();
		}

		conexao.close();
	    } catch (SQLException e) {
		throw new DAOException("Erro ao fechar a conexão. Causa: "
			+ e.getMessage(), e);
	    }
	}
    }

    public final void iniciarTransacao() throws DAOException {
	try {
	    if (conexao == null) {
		abrirConexao();
	    }

	    conexao.setAutoCommit(false);
	} catch (SQLException e) {
	    throw new DAOException("Erro ao habilitar transação. Causa: "
		    + e.getMessage(), e);
	}
    }

    public final void finalizarTransacao() throws DAOException {
	try {
	    if (conexao == null) {
		abrirConexao();
	    }
	    conexao.setAutoCommit(true);
	} catch (SQLException e) {
	    throw new DAOException("Erro ao finalizar transação. Causa: "
		    + e.getMessage(), e);
	}
    }
}
