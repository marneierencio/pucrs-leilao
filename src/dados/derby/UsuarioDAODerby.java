/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados.derby;

import dados.DAO;
import dados.DAOException;
import dados.UsuarioDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import negocio.pojos.Usuario;

/**
 *
 * @author Marnei
 */
public class UsuarioDAODerby extends DAO implements UsuarioDAO {

    @Override
    public Usuario criar(Usuario usuario) throws DAOException {
        try {
            abrirConexao();
            try {
                stmt = conexao.prepareStatement("INSERT INTO Categorias ("
                        + "cpf_cnpj, nome, email, senha, versao) VALUES (?, ?, ?, ?, ?) ",
                        Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, usuario.getCpfCnpj());
                stmt.setString(2, usuario.getNome());
                stmt.setString(3, usuario.getEmail());
                stmt.setString(4, usuario.getSenha());
                stmt.setInt(5, 1);

                stmt.executeUpdate();
                ResultSet rs = stmt.getGeneratedKeys();
                int codigo = rs.next() ? rs.getInt(1) : 0;
                usuario.setCodigo(codigo);

                return usuario;

            } catch (SQLException e) {
                throw new DAOException("Erro ao inserir o registro. Causa: "
                        + e.getMessage(), e);
            }
        } finally {
            fecharConexao();
        }
    }

    @Override
    public Usuario recuperar(Integer codigo) throws DAOException {
        Usuario usuario = null;
        try {
            abrirConexao();
            try {
                stmt = conexao.prepareStatement("SELECT * FROM Usuarios WHERE codigo =? ");
                stmt.setInt(1, codigo);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    usuario = new Usuario(rs.getInt("codigo"),
                            rs.getString("cpf_cnpj"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getInt("versao"));
                }
            } catch (SQLException e) {
                throw new DAOException("Erro ao obter o registro. Causa: "
                        + e.getMessage(), e);
            }
        } finally {
            fecharConexao();
        }
        return usuario;
    }

    @Override
    public Usuario atualizar(Usuario usuario) throws DAOException {
        Usuario usuarioAtualizado = null;
        try {
            abrirConexao();
            if (recuperar(usuario.getCodigo()).getVersao() == usuario.getVersao()) {
                try {
                    stmt = conexao.prepareStatement("UPDATE Usuarios SET cpf_cnpj = ?, "
                            + "nome = ?, "
                            + "email = ?, "
                            + "senha = ?, "
                            + "versao = ? WHERE codigo = ? ");
                    stmt.setString(1, usuario.getCpfCnpj());
                    stmt.setString(2, usuario.getNome());
                    stmt.setString(3, usuario.getEmail());
                    stmt.setString(4, usuario.getSenha());
                    stmt.setInt(5, usuario.getVersao() + 1);

                    usuarioAtualizado = usuario;

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
        return usuarioAtualizado;
    }

    @Override
    public Usuario remover(Usuario usuario) throws DAOException {
        try {
            abrirConexao();

            try {
                stmt = conexao.prepareStatement("DELETE Usuarios WHERE codigo = ? ");
                stmt.setInt(1, usuario.getCodigo());
                stmt.executeUpdate();

                return usuario;

            } catch (SQLException e) {
                throw new DAOException("Erro ao excluir o registro. Causa: "
                        + e.getMessage(), e);
            }

        } finally {
            fecharConexao();
        }
    }

    @Override
    public List<Usuario> listar(Usuario criterio) throws DAOException {
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            abrirConexao();
            try {
                StringBuilder sql = new StringBuilder();
                sql.append("SELECT * FROM Usuarios WHERE 1 = 1 ");
//(Integer codigo, String cpfCnpj, String nome, String email, String senha, Integer versao)
                if (criterio.getCodigo() != null
                        || criterio.getSenha() != null
                        || criterio.getVersao() != null) {
                    throw new DAOException("Erro ao obter os registros. Causa: O critério usado não foi implementado.");
                }

                if (criterio.getCpfCnpj() != null) {
                    sql.append(" AND LOWER(cpf_cnpj) LIKE ?");
                }
                if (criterio.getNome() != null) {
                    sql.append(" AND LOWER(nome) LIKE ?");
                }
                if (criterio.getEmail() != null) {
                    sql.append(" AND LOWER(email) LIKE ?");
                }

                stmt = conexao.prepareStatement(sql.toString());

                int cont = 0;
                if (criterio.getCpfCnpj() != null) {
                    stmt.setString(++cont, "%"
                            + criterio.getCpfCnpj().toLowerCase() + "%");
                }

                if (criterio.getNome() != null) {
                    stmt.setString(++cont, "%"
                            + criterio.getNome().toLowerCase() + "%");
                }

                if (criterio.getEmail() != null) {
                    stmt.setString(++cont, "%"
                            + criterio.getEmail().toLowerCase() + "%");
                }

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Usuario usuario = new Usuario(rs.getInt("codigo"),
                            rs.getString("cpf_cnpj"),
                            rs.getString("nome"),
                            rs.getString("email"),
                            rs.getString("senha"),
                            rs.getInt("versao"));
                    usuarios.add(usuario);
                }
            } catch (SQLException e) {
                throw new DAOException("Erro ao obter os registros. Causa: "
                        + e.getMessage(), e);
            }
        } finally {
            fecharConexao();
        }
        return usuarios;
    }
}
