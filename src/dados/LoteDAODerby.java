/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import negocio.BemDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocio.LoteDAO;
import negocio.pojos.Bem;
import negocio.pojos.Categoria;
import negocio.pojos.Leilao;
import negocio.pojos.Lote;

/**
 *
 * @author Marnei
 */
public class LoteDAODerby extends DAO implements LoteDAO {

	private static LoteDAODerby ref;

	public static LoteDAODerby getInstance() throws DAOException {
		if (ref == null) {
			ref = new LoteDAODerby();
		}
		return ref;
	}

	LoteDAODerby() throws DAOException {

	}

//	private Integer codigo;
//	private List<Bem> bens;
//	private Leilao leilao;
//	private Integer versao;
	@Override
	public Lote criar(Lote lote) throws DAOException {
		try {
			abrirConexao();
			try {
				stmt = conexao.prepareStatement("INSERT INTO Lotes (cod_leilao, versao) VALUES (?, ?) ",
					Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, lote.getLeilao().getCodigo());
				stmt.setInt(2, 1);

				stmt.executeUpdate();
				ResultSet rs = stmt.getGeneratedKeys();
				int codigo = rs.next() ? rs.getInt(1) : 0;
				lote.setCodigo(codigo);

				return lote;

			} catch (SQLException e) {
				throw new DAOException("Erro ao inserir o registro. Causa: "
					+ e.getMessage(), e);
			}
		} finally {
			fecharConexao();
		}
	}

	@Override
	public Lote recuperar(Integer codigo) throws DAOException {
		Lote lote = null;
		try {
			abrirConexao();
			try {
				stmt = conexao.prepareStatement("SELECT * FROM Lotes WHERE codigo = ? ");
				stmt.setInt(1, codigo);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					Leilao leilao = new LeilaoDAODerby().recuperar(rs.getInt("cod_categoria"));
					List<Bem> bens = new ArrayList<>();

					lote = new Lote(rs.getInt("codigo"), bens, leilao, rs.getInt("versao"));
				}
			} catch (SQLException e) {
				throw new DAOException("Erro ao obter o registro. Causa: "
					+ e.getMessage(), e);
			}
		} finally {
			fecharConexao();
		}
		return lote;
	}

	@Override
	public Lote atualizar(Lote lote) throws DAOException {
		Lote loteAtualizado = null;
//		try {
//			abrirConexao();
//			if (recuperar(lote.getCodigo()).getVersao() == lote.getVersao()) {
//				try {
//					stmt = conexao.prepareStatement("UPDATE Lotes SET descricao = ?, descricao_detalhada = ?, cod_categoria = ?, cod_lote = ?, versao = ? WHERE codigo = ? ");
//					stmt.setString(1, bem.getDescricao());
//					stmt.setString(2, bem.getDescricaoDetalhada());
//					stmt.setInt(3, bem.getCategoria().getCodigo());
//					stmt.setInt(4, bem.getVersao() + 1);
//					stmt.setInt(5, bem.getLote().getCodigo());
//					stmt.setInt(5, bem.getCodigo());
//
//					bemAtualizado = bem;
//
//				} catch (SQLException e) {
//					throw new DAOException(
//						"Erro ao alterar o registro. Causa: "
//						+ e.getMessage(), e);
//				}
//			} else {
//				throw new DAOException(
//					"Erro ao alterar o registro. Causa: O registro foi alterado por outro usuário. Recarregue a página para obter o registro atualizado.");
//			}
//		} finally {
//			fecharConexao();
//		}
		return loteAtualizado;
	}

	@Override
	public Lote remover(Lote lote) throws DAOException {
		try {
			abrirConexao();

			try {
				stmt = conexao.prepareStatement("DELETE Lotes WHERE codigo = ? ");
				stmt.setInt(1, lote.getCodigo());
				stmt.executeUpdate();

				return lote;

			} catch (SQLException e) {
				throw new DAOException("Erro ao excluir o registro. Causa: "
					+ e.getMessage(), e);
			}

		} finally {
			fecharConexao();
		}
	}

	@Override
	public List<Lote> listar(Lote criterio) throws DAOException {
		List<Lote> lotes = new ArrayList<>();
//		try {
//			abrirConexao();
//			try {
//				StringBuilder sql = new StringBuilder();
//				sql.append("SELECT * FROM Lotes WHERE 1 = 1 ");
//
//				if (criterio.getDescricao() != null) {
//					sql.append(" AND LOWER(descricao) LIKE ?");
//				}
//				if (criterio.getLote() != null) {
//					sql.append(" AND cod_lote LIKE ?");
//				}
//				
//				stmt = conexao.prepareStatement(sql.toString());
//
//				int cont = 0;
//				if (criterio.getDescricao() != null) {
//					stmt.setString(++cont, "%"
//						+ criterio.getDescricao().toLowerCase() + "%");
//				}
//				if (criterio.getLote() != null) {
//					stmt.setInt(++cont, criterio.getLote().getCodigo());
//				}
//				
//				ResultSet rs = stmt.executeQuery();
//
//				while (rs.next()) {
//					Categoria categoria = new CategoriaDAODerby().recuperar(rs.getInt("cod_categoria"));
//					Lote lote = new LoteDAODerby().recuperar(rs.getInt("cod_lote"));
//					Bem bem = new Bem(rs.getInt("codigo"), rs.getString("descricao"), rs.getString("descricao_detalhada"), categoria, lote, rs.getInt("versao"));
//					bens.add(bem);
//				}
//			} catch (SQLException e) {
//				throw new DAOException("Erro ao obter os registros. Causa: "
//					+ e.getMessage(), e);
//			}
//		} finally {
//			fecharConexao();
//		}
		return lotes;
	}
}
