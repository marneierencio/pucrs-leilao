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
import java.util.Objects;
import negocio.pojos.Bem;
import negocio.pojos.Categoria;
import negocio.pojos.Lote;

/**
 *
 * @author Marnei
 */
public class BemDAODerby extends DAO implements BemDAO {

	private static BemDAODerby ref;

	public static BemDAODerby getInstance() throws DAOException {
		if (ref == null) {
			ref = new BemDAODerby();
		}
		return ref;
	}

	private BemDAODerby() throws DAOException {
	}

	@Override
	public Bem criar(Bem bem) throws DAOException {
		try {
			abrirConexao();
			try {
				stmt = conexao.prepareStatement("INSERT INTO Bens (descricao, descricao_detalhada, cod_lote, cod_categoria, versao) VALUES (?, ?, ?, ?, ?) ",
					Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, bem.getDescricao());
				stmt.setString(2, bem.getDescricaoDetalhada());
				stmt.setInt(3, bem.getCategoria().getCodigo());
				stmt.setInt(4, bem.getLote().getCodigo());
				stmt.setInt(5, 1);

				stmt.executeUpdate();
				ResultSet rs = stmt.getGeneratedKeys();
				int codigo = rs.next() ? rs.getInt(1) : 0;
				bem.setCodigo(codigo);

				return bem;

			} catch (SQLException e) {
				throw new DAOException("Erro ao inserir o registro. Causa: "
					+ e.getMessage(), e);
			}
		} finally {
			fecharConexao();
		}
	}

	@Override
	public Bem recuperar(Integer codigo) throws DAOException {
		Bem bem = null;
		try {
			abrirConexao();
			try {
				stmt = conexao.prepareStatement("SELECT * FROM Bens WHERE codigo = ? ");
				stmt.setInt(1, codigo);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					Categoria categoria = new CategoriaDAODerby().recuperar(rs.getInt("cod_categoria"));
					Lote lote = new LoteDAODerby().recuperar(rs.getInt("cod_lote"));
					bem = new Bem(rs.getInt("codigo"), rs.getString("descricao"), rs.getString("descricao_detalhada"), lote, categoria, rs.getInt("versao"));
				}
			} catch (SQLException e) {
				throw new DAOException("Erro ao obter o registro. Causa: "
					+ e.getMessage(), e);
			}
		} finally {
			fecharConexao();
		}
		return bem;
	}

	@Override
	public Bem atualizar(Bem bem) throws DAOException {
		Bem bemAtualizado = null;
		try {
			abrirConexao();
			if (Objects.equals(recuperar(bem.getCodigo()).getVersao(), bem.getVersao())) {
				try {
					stmt = conexao.prepareStatement("UPDATE Bens SET descricao = ?, descricao_detalhada = ?, cod_lote = ?, cod_categoria = ?, versao = ? WHERE codigo = ? ");
					stmt.setString(1, bem.getDescricao());
					stmt.setString(2, bem.getDescricaoDetalhada());
					stmt.setInt(3, bem.getLote().getCodigo());
					stmt.setInt(4, bem.getCategoria().getCodigo());
					stmt.setInt(5, bem.getVersao() + 1);
					stmt.setInt(6, bem.getCodigo());

					bemAtualizado = bem;

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
		return bemAtualizado;
	}

	@Override
	public Bem remover(Bem bem) throws DAOException {
		try {
			abrirConexao();

			try {
				stmt = conexao.prepareStatement("DELETE Bens WHERE codigo = ? ");
				stmt.setInt(1, bem.getCodigo());
				stmt.executeUpdate();

				return bem;

			} catch (SQLException e) {
				throw new DAOException("Erro ao excluir o registro. Causa: "
					+ e.getMessage(), e);
			}

		} finally {
			fecharConexao();
		}
	}

	@Override
	public List<Bem> listar(Bem criterio) throws DAOException {
		List<Bem> bens = new ArrayList<>();
		try {
			abrirConexao();
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM Bens WHERE 1 = 1 ");

				if (criterio.getDescricao() != null) {
					sql.append(" AND LOWER(descricao) LIKE ?");
				}
				if (criterio.getLote() != null) {
					sql.append(" AND cod_lote LIKE ?");
				}
				stmt = conexao.prepareStatement(sql.toString());

				int cont = 0;
				if (criterio.getDescricao() != null) {
					stmt.setString(++cont, "%"
						+ criterio.getDescricao().toLowerCase() + "%");
				}
				if (criterio.getLote() != null) {
					stmt.setInt(++cont, criterio.getLote().getCodigo());
				}
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Categoria categoria = new CategoriaDAODerby().recuperar(rs.getInt("cod_categoria"));
					Lote lote = new LoteDAODerby().recuperar(rs.getInt("cod_lote"));
					Bem bem = new Bem(rs.getInt("codigo"), rs.getString("descricao"), rs.getString("descricao_detalhada"), lote, categoria, rs.getInt("versao"));
					bens.add(bem);
				}
			} catch (SQLException e) {
				throw new DAOException("Erro ao obter os registros. Causa: "
					+ e.getMessage(), e);
			}
		} finally {
			fecharConexao();
		}
		return bens;
	}
}
