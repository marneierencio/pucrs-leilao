/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Marnei
 */
public abstract class DAO<T> {

    private static final DataSource DATA_SOURCE = inicializarDataSource();
    protected Connection conexao;
    protected PreparedStatement stmt;

    private static DataSource inicializarDataSource() {
        try {
            Context contexto = new InitialContext();
            Context envContexto = (Context) contexto.lookup("java:comp/env");

            DataSource dataSource = (DataSource) envContexto
                    .lookup("jdbc/leilao");

            return dataSource;
        } catch (NamingException e) {
            throw new Error("Erro ao inicializar o datasource. Causa: "
                    + e.getMessage(), e);
        }
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

    private final void criarBancoDados() throws DAOException {
        try {
            if (conexao == null) {
                abrirConexao();
            }
            try {
                stmt = conexao
                        .prepareStatement("CREATE TABLE Lances ( "
                                + "cod_lance INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
                                + "valor DOUBLE, "
                                + "data DATE, "
                                + "hora TIME, "
                                + "cpf_cnpj VARCHAR(20), "
                                + "cod_leilao INTEGER, "
                                + "tipo_leilao VARCHAR(20),"
                                + "versao INTEGER "
                                + "); "
                                + ""
                                + "CREATE TABLE Leiloes ( "
                                + "cod_leilao INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
                                + "tipo_leilao VARCHAR(20), "
                                + "forma_lance VARCHAR(20), "
                                + "data_inicio DATE, "
                                + "hora_inicio TIME, "
                                + "data_termino DATE, "
                                + "hora_termino TIME, "
                                + "preco DOUBLE, "
                                + "cpf_cnpj VARCHAR(20), "
                                + "versao INTEGER "
                                + "); "
                                + ""
                                + "CREATE TABLE Bens ( "
                                + "cod_bem INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
                                + "descricao VARCHAR(255), "
                                + "descricao_detalhada VARCHAR(MAX), "
                                + "cod_leilao INTEGER, "
                                + "tipo_leilao VARCHAR(20), "
                                + "cod_categoria INTEGER,"
                                + "versao INTEGER, "
                                + "FOREIGN KEY(cod_leilao,tipo_leilao) REFERENCES Leiloes (cod_leilao,tipo_leilao) "
                                + "); "
                                + ""
                                + "CREATE TABLE Categorias ( "
                                + "cod_categoria INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
                                + "descricao VARCHAR(255), "
                                + "versao INTEGER "
                                + "); "
                                + ""
                                + "CREATE TABLE Usuarios ( "
                                + "cpf_cnpj VARCHAR(20) PRIMARY KEY, "
                                + "nome VARCHAR(255), "
                                + "email VARCHAR(255), "
                                + "senha VARCHAR(255),"
                                + "versao INTEGER "
                                + "); "
                                + ""
                                + "ALTER TABLE Lances ADD FOREIGN KEY(cpf_cnpj) REFERENCES Usuarios (cpf_cnpj); "
                                + "ALTER TABLE Lances ADD FOREIGN KEY(cod_leilao,tipo_leilao) REFERENCES Leiloes (cod_leilao,tipo_leilao); "
                                + "ALTER TABLE Leiloes ADD FOREIGN KEY(cpf_cnpj) REFERENCES Usuarios (cpf_cnpj); "
                                + "ALTER TABLE Bens ADD FOREIGN KEY(cod_categoria) REFERENCES Categorias (cod_categoria); ");

                stmt.executeUpdate();

            } catch (SQLException e) {
                throw new DAOException(
                        "Erro ao criar o banco de dados. Causa: "
                        + e.getMessage(), e);
            }

        } finally {
            fecharConexao();
        }
    }

    private final void popularBancoDados() throws DAOException {
        try {
            if (conexao == null) {
                abrirConexao();
            }
            try {
                stmt = conexao
                        .prepareStatement("INSERT INTO Categorias (descricao) VALUES('Mobiliário', 1); "
                                + "INSERT INTO Categorias (descricao, versao) VALUES ('Informática', 1); "
                                + "INSERT INTO Categorias (descricao, versao) VALUES ('Vestuário', 1); "
                                + "INSERT INTO Categorias (descricao, versao) VALUES ('Eletrodomésticos', 1); "
                                + ""
                                + "INSERT INTO Usuarios (cpf_cnpj, nome, email, senha, versao) VALUES ('11111111111', 'Ana', 'ana@gmail.com', '123456', 1); "
                                + "INSERT INTO Usuarios (cpf_cnpj, nome, email, senha, versao) VALUES ('22222222222', 'Beatriz', 'beatriz@gmail.com', '123456', 1); "
                                + "INSERT INTO Usuarios (cpf_cnpj, nome, email, senha, versao) VALUES ('33333333333', 'Carla', 'carla@gmail.com', '123456', 1); "
                                + "INSERT INTO Usuarios (cpf_cnpj, nome, email, senha, versao) VALUES ('44444444444', 'Daniela', 'daniela@gmail.com', '123456', 1); "
                                + "INSERT INTO Usuarios (cpf_cnpj, nome, email, senha, versao) VALUES ('55555555555', 'Elaine', 'elaine@gmail.com', '123456', 1); "
                                + ""
                                + "INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Cadeiras de jantar', '4 cadeiras de jantar com assento estofado e encosto alto', 1, 1, 1); "
                                + "INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Mesa de jantar', 'Mesa de jantar com tampo de vidro para até 6 lugares', 1, 1, 1); "
                                + "INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Televisão a cores 39 polegadas', 'Televisão a cores de tubo, 39 polegadas, da marca Telefunken, em ótimo estado', 4, 2, 1); "
                                + "INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Videogame Atari 2600', 'Videogame Atari 2600 com 2 joysticks', 4, 2, 1); "
                                + "INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Jogos de atari', '5 cartuchos para videogame Atari: Pacman, Enduro, River Raid, Keystone Cappers e Pitfall', 4, 2, 1); "
                                + "INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Notebook IBM 486', 'Notebook IBM processador 486 DX2/66, tela monocromática, 8MB RAM, 500MB de disco, bateria em bom estado. Acompanha carregador.', 2, 3, 1); "
                                + "INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Tênis Mizuno tamanho 42', 'Par de tênis Mizuno azul e amarelo, modelo Kaiano, tamanho 42', 3, 4, 1); "
                                + "INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Escrivaninha em cerejeira', 'Escrivaninha em cerejeira com prateleiras para livros tamanho 2,5x0,9x0,7m (CxLxA)', 1, 5, 1); "
                                + "INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Cadeira de escritório', 'Cadeira de escritório marca Giroflex com estofamento laranja, manchada de café', 1, 5, 1); "
                                + "INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Pacote com 300 fitas VHS', '300 fitas VHS, diversos títulos, todas testadas com garantia', 4, 6, 1); "
                                + "INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Reatores de lâmpadas fluorescentes', '30 reatores para lâmpadas fluorescentes', 4, 7, 1); "
                                + "INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Mesas de escritório', '5 mesas de escritório modelo manjuba 23 marca Escriba', 1, 8, 1); "
                                + "INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Projetor HD 6000lm', 'Projetor HD 6000 lumens com entrada HDMI e USB', 2, 9, 1); "
                                + "");

                stmt.executeUpdate();

            } catch (SQLException e) {
                throw new DAOException(
                        "Erro ao popular o banco de dados. Causa: "
                        + e.getMessage(), e);
            }

        } finally {
            fecharConexao();
        }
    }
}
