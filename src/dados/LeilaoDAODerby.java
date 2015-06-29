/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import negocio.LeilaoDAO;
import dados.DAO;
import dados.DAOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocio.pojos.FormaLance;
import negocio.pojos.Leilao;
import negocio.pojos.Natureza;
import negocio.pojos.Usuario;

/**
 *
 * @author Marnei
 */
public class LeilaoDAODerby  extends DAO implements LeilaoDAO {
    private static LeilaoDAODerby ref;
    
    public static LeilaoDAODerby getInstance() throws DAOException {
        if (ref == null)
            ref = new LeilaoDAODerby();
        return ref;
    }
    
    LeilaoDAODerby() throws DAOException {

	}
    
    @Override
    public Leilao criar(Leilao leilao) throws DAOException {
        try {
            abrirConexao();
            try {
                stmt = conexao.prepareStatement("INSERT INTO Leiloes ("
                        + "natureza, "
                        + "forma_lance, "
                        + "data_inicio, "
                        + "hora_inicio, "
                        + "data_termino, "
                        + "hora_termino, "
                        + "preco, "
                        + "cod_usuario, "
                        + "versao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ",
                        
                        Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, leilao.getNatureza().toString());
                stmt.setString(2, leilao.getFormaLance().toString());
                stmt.setDate(3, (Date) leilao.getDataInicio());
                stmt.setTime(4, leilao.getHoraInicio());
                stmt.setDate(5, (Date) leilao.getDataTermino());
                stmt.setTime(6, leilao.getHoraTermino());
                stmt.setDouble(7, leilao.getPreco());
                stmt.setInt(8, leilao.getUsuario().getCodigo());
                stmt.setInt(9, 1);

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                int codigo = rs.next() ? rs.getInt(1) : 0;
                leilao.setCodigo(codigo);

                return leilao;

            } catch (SQLException e) {
                throw new DAOException("Erro ao inserir o registro. Causa: "
                        + e.getMessage(), e);
            }
        } finally {
            fecharConexao();
        }
    }

    @Override
    public Leilao recuperar(Integer codigo) throws DAOException {
        Leilao leilao = null;
        try {
            abrirConexao();
            try {
                stmt = conexao.prepareStatement("SELECT * FROM Leiloes WHERE codigo =? ");
                stmt.setInt(1, codigo);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    Natureza n;
                    FormaLance f;
                    Usuario u = new UsuarioDAODerby().recuperar(rs.getInt("cod_usuario"));
                    if("DEMANDA".equals(rs.getString("natureza")))
                        n = Natureza.DEMANDA;
                    else
                        n = Natureza.OFERTA;
                    if("ABERTO".equals(rs.getString("forma_lance")))
                        f = FormaLance.ABERTO;
                    else
                        f = FormaLance.FECHADO;
                    
                    leilao = new Leilao(rs.getInt("codigo"), 
                            n, 
                            f, 
                            rs.getDate("data_inicio"), 
                            rs.getTime("hora_inicio"), 
                            rs.getDate("data_termino"), 
                            rs.getTime("hora_termino"), 
                            rs.getDouble("preco"), 
                            u, 
                            rs.getInt("versao"));
                }
            } catch (SQLException e) {
                throw new DAOException("Erro ao obter o registro. Causa: "
                        + e.getMessage(), e);
            }
        } finally {
            fecharConexao();
        }
        return leilao;
    }

    @Override
    public Leilao atualizar(Leilao leilao) throws DAOException {
        Leilao leilaoAtualizado = null;
        try {
            abrirConexao();
            if (recuperar(leilao.getCodigo()).getVersao() == leilao.getVersao()) {
                try {
                    stmt = conexao.prepareStatement("UPDATE Leiloes SET natureza = ?, "
                            + "forma_lance = ?, "
                            + "data_inicio = ?, "
                            + "hora_inicio = ?, "
                            + "data_termino = ?, "
                            + "hora_termino = ?, "
                            + "preco = ?,"
                            + "cod_usuario = ?, "
                            + "versao = ? WHERE codigo = ? ");
                    stmt.setString(1, leilao.getNatureza().toString());
                    stmt.setString(2, leilao.getFormaLance().toString());
                    stmt.setDate(3, (Date) leilao.getDataInicio());
                    stmt.setTime(4, leilao.getHoraInicio());
                    stmt.setDate(5, (Date) leilao.getDataTermino());
                    stmt.setTime(6, leilao.getHoraTermino());
                    stmt.setDouble(7, leilao.getPreco());
                    stmt.setInt(8, leilao.getUsuario().getCodigo());
                    stmt.setInt(9, leilao.getVersao()+1);
                    stmt.setInt(10, leilao.getCodigo());

                    leilaoAtualizado = leilao;

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
        return leilaoAtualizado;
    }

    @Override
    public Leilao remover(Leilao leilao) throws DAOException {
        try {
            abrirConexao();

            try {
                stmt = conexao.prepareStatement("DELETE Leiloes WHERE codigo = ? ");
                stmt.setInt(1, leilao.getCodigo());
                stmt.executeUpdate();

                return leilao;

            } catch (SQLException e) {
                throw new DAOException("Erro ao excluir o registro. Causa: "
                        + e.getMessage(), e);
            }

        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Leilao> listar(Leilao criterio) throws DAOException {
        List<Leilao> leiloes = new ArrayList<>();
        try {
            abrirConexao();
            try {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT * FROM Leiloes WHERE 1 = 1 ");
                if (criterio.getCodigo() != null || 
                        criterio.getDataInicio() != null ||
                        criterio.getHoraInicio() != null ||
                        criterio.getDataTermino() != null ||
                        criterio.getHoraTermino() != null ||
                        criterio.getPreco() != null ||
                        criterio.getVersao() != null) {
                    throw new DAOException("Erro ao obter os registros. Causa: O critério usado não foi implementado.");
                }
                if (criterio.getNatureza() != null) {
                    sql.append(" AND natureza LIKE ?");
                }
                if (criterio.getFormaLance() != null) {
                    sql.append(" AND forma_lance LIKE ?");
                }
                if (criterio.getUsuario() != null) {
                    sql.append(" AND cod_usuario LIKE ?");
                }

                stmt = conexao.prepareStatement(sql.toString());

                int cont = 0;
                if (criterio.getNatureza() != null) {
                    stmt.setString(++cont, criterio.getNatureza().toString());
                }
                if (criterio.getFormaLance() != null) {
                    stmt.setString(++cont, criterio.getFormaLance().toString());
                }
                if (criterio.getUsuario() != null) {
                    stmt.setInt(++cont, criterio.getUsuario().getCodigo());
                }
                
                ResultSet rs = stmt.executeQuery();
    //public Leilao(Integer codigo, Natureza natureza, FormaLance formaLance, Date dataInicio, Time horaInicio, 
    //Date dataTermino, Time horaTermino, Double preco, Usuario usuario, Integer versao) {

                while (rs.next()) {
                    Natureza n;
                    FormaLance f;
                    Usuario u = new UsuarioDAODerby().recuperar(rs.getInt("cod_cod_usuario"));
                    if("DEMANDA".equals(rs.getString("natureza")))
                        n = Natureza.DEMANDA;
                    else
                        n = Natureza.OFERTA;
                    if("ABERTO".equals(rs.getString("forma_lance")))
                        f = FormaLance.ABERTO;
                    else
                        f = FormaLance.FECHADO;
                    
                    Leilao leilao = new Leilao(rs.getInt("codigo"), 
                            n, 
                            f, 
                            rs.getDate("data_inicio"), 
                            rs.getTime("hora_inicio"), 
                            rs.getDate("data_termino"), 
                            rs.getTime("hora_termino"), 
                            rs.getDouble("preco"), 
                            u, 
                            rs.getInt("versao"));
                    leiloes.add(leilao);
                }
            } catch (SQLException e) {
                throw new DAOException("Erro ao obter os registros. Causa: "
                        + e.getMessage(), e);
            }
        } finally {
            fecharConexao();
        }
        return leiloes;
    }    
}
