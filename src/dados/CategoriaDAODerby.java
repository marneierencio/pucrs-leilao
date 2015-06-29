/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import negocio.CategoriaDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocio.pojos.Categoria;

/**
 *
 * @author Marnei
 */
public class CategoriaDAODerby extends DAO implements CategoriaDAO {

	private static CategoriaDAODerby ref;

	public static CategoriaDAODerby getInstance() throws DAOException {
		if (ref == null) {
			ref = new CategoriaDAODerby();
		}
		return ref;
	}

	CategoriaDAODerby() throws DAOException {

	}

	@Override
	public Categoria criar(Categoria categoria) throws DAOException {
		try {
			abrirConexao();
			try {
				stmt = conexao.prepareStatement("INSERT INTO Categorias (descricao, versao) VALUES (?, ?) ",
					Statement.RETURN_GENERATED_KEYS);
				stmt.setString(1, categoria.getDescricao());
				stmt.setInt(2, 1);

				stmt.executeUpdate();
				ResultSet rs = stmt.getGeneratedKeys();
				int codigo = rs.next() ? rs.getInt(1) : 0;
				categoria.setCodigo(codigo);

				return categoria;

			} catch (SQLException e) {
				throw new DAOException("Erro ao inserir o registro. Causa: "
					+ e.getMessage(), e);
			}
		} finally {
			fecharConexao();
		}
	}

	@Override
	public Categoria recuperar(Integer codigo) throws DAOException {
		Categoria categoria = null;
		try {
			abrirConexao();
			try {
				stmt = conexao.prepareStatement("SELECT * FROM Categorias WHERE codigo =? ");
				stmt.setInt(1, codigo);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					categoria = new Categoria(rs.getInt("codigo"), rs.getString("descricao"), rs.getInt("versao"));
				}
			} catch (SQLException e) {
				throw new DAOException("Erro ao obter o registro. Causa: "
					+ e.getMessage(), e);
			}
		} finally {
			fecharConexao();
		}
		return categoria;
	}

	@Override
	public Categoria atualizar(Categoria categoria) throws DAOException {
		Categoria categoriaAtualizada = null;
		try {
			if (recuperar(categoria.getCodigo()).getVersao() == categoria.getVersao()) {
				try {
					abrirConexao();
					stmt = conexao.prepareStatement("UPDATE Categorias SET descricao = ?, versao = ? WHERE codigo = ? ");
					stmt.setString(1, categoria.getDescricao());
					stmt.setInt(2, categoria.getVersao() + 1);
					stmt.setInt(3, categoria.getCodigo());
					stmt.executeUpdate();
					categoriaAtualizada = categoria;

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
		return categoriaAtualizada;
	}

	@Override
	public Categoria remover(Categoria categoria) throws DAOException {
		try {
			abrirConexao();

			try {
				stmt = conexao.prepareStatement("DELETE Categorias WHERE codigo = ? ");
				stmt.setInt(1, categoria.getCodigo());
				stmt.executeUpdate();

				return categoria;

			} catch (SQLException e) {
				throw new DAOException("Erro ao excluir o registro. Causa: "
					+ e.getMessage(), e);
			}

		} finally {
			fecharConexao();
		}
	}

	@Override
	public List<Categoria> listar(Categoria criterio) throws DAOException {
		List<Categoria> categorias = new ArrayList<Categoria>();
		try {
			abrirConexao();
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM Categorias WHERE 1 = 1 ");
				if (criterio != null) {
					if (criterio.getDescricao() != null) {
						sql.append(" AND LOWER(descricao) LIKE ?");
					}
				}
				stmt = conexao.prepareStatement(sql.toString());

				int cont = 0;
				if (criterio != null) {
					if (criterio.getDescricao() != null) {
						stmt.setString(++cont, "%"
							+ criterio.getDescricao().toLowerCase() + "%");
					}
				}
				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {
					Categoria categoria = new Categoria(rs.getInt("codigo"), rs.getString("descricao"), rs.getInt("versao"));
					categorias.add(categoria);
				}
			} catch (SQLException e) {
				throw new DAOException("Erro ao obter os registros. Causa: "
					+ e.getMessage(), e);
			}
		} finally {
			fecharConexao();
		}
		return categorias;
	}
}
