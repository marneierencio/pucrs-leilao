/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import negocio.LanceDAO;
import dados.DAO;
import dados.DAOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocio.pojos.Lance;
import negocio.pojos.Leilao;
import negocio.pojos.Usuario;

/**
 *
 * @author Marnei
 */
public class LanceDAODerby extends DAO implements LanceDAO {
    
    private static LanceDAODerby ref;
    
    public static LanceDAODerby getInstance() throws DAOException {
        if (ref == null)
            ref = new LanceDAODerby();
        return ref;
    }
    
    LanceDAODerby() throws DAOException {

    }
    
    @Override
    public Lance criar(Lance lance) throws DAOException {
        try {
            abrirConexao();
            try {
                stmt = conexao.prepareStatement("INSERT INTO Lances (data, hora, valor, cod_usuario, cod_leilao, versao) VALUES (?, ?, ?, ?, ?, ?) ",
                        Statement.RETURN_GENERATED_KEYS);
                stmt.setDate(1, (Date) lance.getData());
                stmt.setTime(2, lance.getHora());
                stmt.setDouble(3, lance.getValor());
                stmt.setInt(4, lance.getUsuario().getCodigo());
                stmt.setInt(5, lance.getLeilao().getCodigo());
                stmt.setInt(6, 1);

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                int codigo = rs.next() ? rs.getInt(1) : 0;
                lance.setCodigo(codigo);

                return lance;

            } catch (SQLException e) {
                throw new DAOException("Erro ao inserir o registro. Causa: "
                        + e.getMessage(), e);
            }
        } finally {
            fecharConexao();
        }
    }
    
    
    @Override
    public Lance recuperar(Integer codigo) throws DAOException {
        Lance lance = null;
        try {
            abrirConexao();
            try {
                stmt = conexao.prepareStatement("SELECT * FROM Lances WHERE codigo = ? ");
                stmt.setInt(1, codigo);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Usuario usuario = new UsuarioDAODerby().recuperar(rs.getInt("cod_cod_usuario"));
                    Leilao leilao = new LeilaoDAODerby().recuperar(rs.getInt("cod_leilao"));
                    
                    lance = new Lance(rs.getInt("codigo"), rs.getDate("data"), rs.getTime("hora"), rs.getDouble("valor"), usuario, leilao, rs.getInt("versao"));
                }
            } catch (SQLException e) {
                throw new DAOException("Erro ao obter o registro. Causa: "
                        + e.getMessage(), e);
            }
        } finally {
            fecharConexao();
        }
        return lance;
    }

    @Override
    public Lance atualizar(Lance lance) throws DAOException {
        Lance lanceAtualizado = null;
        try {
            abrirConexao();
            if (recuperar(lance.getCodigo()).getVersao() == lance.getVersao()) {
                try {
                    stmt = conexao.prepareStatement("UPDATE Lances SET data = ?, hora = ?, valor = ?, cod_usuario = ?, cod_leilao = ?, versao = ? WHERE codigo = ? ");
                    stmt.setDate(1, (Date) lance.getData());
                    stmt.setTime(2, lance.getHora());
                    stmt.setDouble(3, lance.getValor());
                    stmt.setInt(4, lance.getUsuario().getCodigo());
                    stmt.setInt(5, lance.getLeilao().getCodigo());
                    stmt.setInt(6, lance.getVersao() + 1);
                    stmt.setInt(7, lance.getCodigo());

                    lanceAtualizado = lance;

                } catch (SQLException e) {
                    throw new DAOException(
                            "Erro ao alterar o registro. Causa: "
                            + e.getMessage(), e);
                }
            } else {
                throw new DAOException(
                        "Erro ao alterar o registro. Causa: O registro foi alterado por outro usuário. Recarregue a página para obter o registro atualizado.");
            }
        } finally {
            fecharConexao();
        }
        return lanceAtualizado;
    }

    @Override
    public Lance remover(Lance lance) throws DAOException {
        try {
            abrirConexao();

            try {
                stmt = conexao.prepareStatement("DELETE Lances WHERE codigo = ? ");
                stmt.setInt(1, lance.getCodigo());
                stmt.executeUpdate();

                return lance;

            } catch (SQLException e) {
                throw new DAOException("Erro ao excluir o registro. Causa: "
                        + e.getMessage(), e);
            }

        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Lance> listar(Lance criterio) throws DAOException {
        List<Lance> lances = new ArrayList<Lance>();
        try {
            abrirConexao();
            try {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT * FROM Bens WHERE 1 = 1 ");
                
                //Filtrar por usuário
                if (criterio.getUsuario() != null) {
                    sql.append(" AND cod_usuario = ? ");
                }
                
                //Filtrar por leilao
                if (criterio.getLeilao() != null) {
                    sql.append(" AND cod_leilao = ? ");
                }

                stmt = conexao.prepareStatement(sql.toString());

                int cont = 0;
                if (criterio.getUsuario() != null) {
                    stmt.setInt(++cont, criterio.getUsuario().getCodigo());
                }
                if (criterio.getLeilao() != null) {
                    stmt.setInt(++cont, criterio.getLeilao().getCodigo());
                }
                
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Usuario u = new UsuarioDAODerby().recuperar(rs.getInt("cod_usuario"));
                    Leilao l = new LeilaoDAODerby().recuperar(rs.getInt("cod_leilao"));
                    Lance lance = new Lance(rs.getInt("codigo"), rs.getDate("data"), rs.getTime("hora"), rs.getDouble("valor"), u, l, rs.getInt("versao"));
                    lances.add(lance);
                }
            } catch (SQLException e) {
                throw new DAOException("Erro ao obter os registros. Causa: "
                        + e.getMessage(), e);
            }
        } finally {
            fecharConexao();
        }
        return lances;
    }
}
