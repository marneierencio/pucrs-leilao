/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.UtilDAO;

/**
 *
 * @author Marnei
 */
public class UtilDAODerby extends DAO implements UtilDAO {

	private static UtilDAODerby ref;

	public static UtilDAODerby getInstance() throws DAOException {
		if (ref == null) {
			ref = new UtilDAODerby();
		}
		return ref;
	}

	public UtilDAODerby() throws DAOException {
	}

	@Override
	public Boolean criarBancoDados() throws DAOException {
		Boolean resultado = false;
		try {
			iniciarTransacao();

			Statement sstmt = conexao.createStatement();

			String sql01 = "CREATE TABLE Lances ( "
				+ "codigo INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
				+ "data DATE, "
				+ "hora TIME, "
				+ "valor DOUBLE, "
				+ "cod_usuario INTEGER, "
				+ "cod_leilao INTEGER, "
				+ "versao INTEGER ) ";
			sstmt.execute(sql01);

			String sql02 = "CREATE TABLE Leiloes ( "
				+ "codigo INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
				+ "natureza VARCHAR(20), "
				+ "forma_lance VARCHAR(20), "
				+ "data_inicio DATE, "
				+ "hora_inicio TIME, "
				+ "data_termino DATE, "
				+ "hora_termino TIME, "
				+ "preco DOUBLE, "
				+ "cod_usuario INTEGER, "
				+ "versao INTEGER ) ";
			sstmt.execute(sql02);

			String sql03 = "CREATE TABLE Bens ( "
				+ "codigo INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
				+ "descricao VARCHAR(255), "
				+ "descricao_detalhada VARCHAR(255), "
				+ "cod_categoria INTEGER,"
				+ "versao INTEGER ) ";
			sstmt.execute(sql03);

			String sql04 = "CREATE TABLE Categorias ( "
				+ "codigo INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
				+ "descricao VARCHAR(255), "
				+ "versao INTEGER ) ";
			sstmt.execute(sql04);

			String sql05 = "CREATE TABLE Usuarios ( "
				+ "codigo INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1), "
				+ "cpf_cnpj VARCHAR(20), "
				+ "nome VARCHAR(255), "
				+ "email VARCHAR(255), "
				+ "senha VARCHAR(255),"
				+ "versao INTEGER ) ";
			sstmt.execute(sql05);

			String sql06 = "ALTER TABLE Lances ADD FOREIGN KEY(cod_usuario) REFERENCES Usuarios (codigo) ";
			sstmt.execute(sql06);

			String sql07 = "ALTER TABLE Leiloes ADD FOREIGN KEY(cod_usuario) REFERENCES Usuarios (codigo) ";
			sstmt.execute(sql07);

			String sql08 = "ALTER TABLE Bens ADD FOREIGN KEY(cod_categoria) REFERENCES Categorias (codigo) ";
			sstmt.execute(sql08);

			resultado = true;

			finalizarTransacao();
		} catch (SQLException e) {
			throw new DAOException(
				"Erro ao criar o banco de dados. Causa: "
				+ e.getMessage(), e);
		}
		return resultado;
	}

	public Boolean popularBancoDados() throws DAOException {
		Boolean resultado = false;
		List<String> registros = new ArrayList();

//registros.add("INSERT INTO Categorias (descricao, versao) VALUES(descricao, versao) ");
		registros.add("INSERT INTO Categorias (descricao, versao) VALUES('Mobiliário', 1) ");
		registros.add("INSERT INTO Categorias (descricao, versao) VALUES ('Informática', 1) ");
		registros.add("INSERT INTO Categorias (descricao, versao) VALUES ('Vestuário', 1) ");
		registros.add("INSERT INTO Categorias (descricao, versao) VALUES ('Eletrodomésticos', 1) ");

//registros.add("INSERT INTO Usuarios (cpf_cnpj, nome, email, senha, versao) VALUES (cpf_cnpj, nome, email, senha, versao) ");
		registros.add("INSERT INTO Usuarios (cpf_cnpj, nome, email, senha, versao) VALUES ('00000000000', 'Administrador', 'admin@mail.com', '123', 1) ");
		registros.add("INSERT INTO Usuarios (cpf_cnpj, nome, email, senha, versao) VALUES ('11111111111', 'UsuárioA', 'a@mail.com', '123', 1) ");
		registros.add("INSERT INTO Usuarios (cpf_cnpj, nome, email, senha, versao) VALUES ('22222222222', 'UsuárioB', 'b@mail.com', '123', 1) ");
		registros.add("INSERT INTO Usuarios (cpf_cnpj, nome, email, senha, versao) VALUES ('33333333333', 'UsuárioC', 'c@mail.com', '123', 1) ");
		registros.add("INSERT INTO Usuarios (cpf_cnpj, nome, email, senha, versao) VALUES ('44444444444', 'UsuárioD', 'd@mail.com', '123', 1) ");
		registros.add("INSERT INTO Usuarios (cpf_cnpj, nome, email, senha, versao) VALUES ('55555555555', 'UsuárioE', 'e@mail.com', '123', 1) ");

////registros.add("INSERT INTO Leiloes(natureza, forma_lance, data_inicio, hora_inicio, data_termino, hora_termino, preco, cod_usuario, versao) VALUES ('natureza', 'forma_lance', 'data_inicio', 'hora_inicio', 'data_termino', 'hora_termino', preco, cod_usuario, versao) ");
//		registros.add("INSERT INTO Leiloes(natureza, forma_lance, data_inicio, hora_inicio, data_termino, hora_termino, preco, cod_usuario, versao) VALUES ('OFERTA', 'ABERTO', '2015-06-01', '00:00:00', '2015-06-12', '23:59:59', 300.00, 2, 1) ");
//		registros.add("INSERT INTO Leiloes(natureza, forma_lance, data_inicio, hora_inicio, data_termino, hora_termino, preco, cod_usuario, versao) VALUES ('OFERTA', 'ABERTO', '2015-06-22', '00:00:00', '2015-07-06', '23:59:59', 600.00, 3, 1) ");
//		registros.add("INSERT INTO Leiloes(natureza, forma_lance, data_inicio, hora_inicio, data_termino, hora_termino, preco, cod_usuario, versao) VALUES ('OFERTA', 'FECHADO', '2015-06-01', '00:00:00', '2015-06-15', '23:59:59', 500.00, 4, 1) ");
//		registros.add("INSERT INTO Leiloes(natureza, forma_lance, data_inicio, hora_inicio, data_termino, hora_termino, preco, cod_usuario, versao) VALUES ('DEMANDA', 'ABERTO', '2015-06-22', '00:00:00', '2015-07-13', '23:59:59', 450.00, 5, 1) ");
//		registros.add("INSERT INTO Leiloes(natureza, forma_lance, data_inicio, hora_inicio, data_termino, hora_termino, preco, cod_usuario, versao) VALUES ('DEMANDA', 'FECHADO', '2015-06-17', '00:00:00', '2015-07-02', '23:59:59', 2500.00, 6, 1) ");
//		registros.add("INSERT INTO Leiloes(natureza, forma_lance, data_inicio, hora_inicio, data_termino, hora_termino, preco, cod_usuario, versao) VALUES ('DEMANDA', 'FECHADO', '2015-06-24', '00:00:00', '2015-07-15', '23:59:59', 4000.00, 4, 1) ");
//
////registros.add("INSERT INTO Lotes (cod_leilao, versao) VALUES (cod_leilao, versao) ");
//		registros.add("INSERT INTO Lotes (cod_leilao, versao) VALUES (1, versao) ");
//		registros.add("INSERT INTO Lotes (cod_leilao, versao) VALUES (2, versao) ");
//		registros.add("INSERT INTO Lotes (cod_leilao, versao) VALUES (3, versao) ");
//		registros.add("INSERT INTO Lotes (cod_leilao, versao) VALUES (4, versao) ");
//		registros.add("INSERT INTO Lotes (cod_leilao, versao) VALUES (5, versao) ");
//		registros.add("INSERT INTO Lotes (cod_leilao, versao) VALUES (6, versao) ");
//
////registros.add("INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) ");
//		registros.add("INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Cadeiras de jantar', '4 cadeiras de jantar com assento estofado e encosto alto', 1, 1, 1) ");
//		registros.add("INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Mesa de jantar', 'Mesa de jantar com tampo de vidro para até 6 lugares', 1, 1, 1) ");
//		registros.add("INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Televisão a cores 39 polegadas', 'Televisão a cores de tubo, 39 polegadas, da marca Telefunken, em ótimo estado', 4, 2, 1) ");
//		registros.add("INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Videogame Atari 2600', 'Videogame Atari 2600 com 2 joysticks', 4, 2, 1) ");
//		registros.add("INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Jogos de atari', '5 cartuchos para videogame Atari: Pacman, Enduro, River Raid, Keystone Cappers e Pitfall', 4, 2, 1) ");
//		registros.add("INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Notebook IBM 486', 'Notebook IBM processador 486 DX2/66, tela monocromática, 8MB RAM, 500MB de disco, bateria em bom estado. Acompanha carregador.', 2, 3, 1) ");
//		registros.add("INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Tênis Mizuno tamanho 42', 'Par de tênis Mizuno azul e amarelo, modelo Kaiano, tamanho 42', 3, 4, 1) ");
//		registros.add("INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Escrivaninha em cerejeira', 'Escrivaninha em cerejeira com prateleiras para livros tamanho 2,5x0,9x0,7m (CxLxA)', 1, 5, 1) ");
//		registros.add("INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Cadeira de escritório', 'Cadeira de escritório marca Giroflex com estofamento laranja, manchada de café', 1, 5, 1) ");
//		registros.add("INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Pacote com 300 fitas VHS', '300 fitas VHS, diversos títulos, todas testadas com garantia', 4, 5, 1) ");
//		registros.add("INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Reatores de lâmpadas fluorescentes', '30 reatores para lâmpadas fluorescentes', 4, 6, 1) ");
//		registros.add("INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Mesas de escritório', '5 mesas de escritório modelo manjuba 23 marca Escriba', 1, 6, 1) ");
//		registros.add("INSERT INTO Bens (descricao, descricao_detalhada, cod_categoria, cod_lote, versao) VALUES ('Projetor HD 6000lm', 'Projetor HD 6000 lumens com entrada HDMI e USB', 2, 6, 1) ");
//
////registros.add("INSERT INTO Lances (data, hora, valor, cod_usuario, cod_leilao, versao) VALUES ('data', 'hora', valor, cod_usuario, cod_leilao, versao) ");
//		registros.add("INSERT INTO Lances (data, hora, valor, cod_usuario, cod_leilao, versao) VALUES ('2015-06-03', '15:12:34', 310.00, 3, 1, 1) ");
//		registros.add("INSERT INTO Lances (data, hora, valor, cod_usuario, cod_leilao, versao) VALUES ('2015-06-09', '09:10:11', 350.00, 4, 1, 1) ");
//		registros.add("INSERT INTO Lances (data, hora, valor, cod_usuario, cod_leilao, versao) VALUES ('2015-06-09', '17:18:19', 360.00, 3, 1, 1) ");
//		registros.add("INSERT INTO Lances (data, hora, valor, cod_usuario, cod_leilao, versao) VALUES ('2015-06-22', '12:13:14', 620.00, 4, 2, 1) ");
//		registros.add("INSERT INTO Lances (data, hora, valor, cod_usuario, cod_leilao, versao) VALUES ('2015-06-23', '17:18:19', 630.00, 2, 2, 1) ");

		try {
			iniciarTransacao();
			for (String registro : registros) {
				stmt = conexao.prepareStatement(registro);
				stmt.executeUpdate();
			}
			resultado = true;

			finalizarTransacao();
		} catch (SQLException e) {
			throw new DAOException(
				"Erro ao popular o banco de dados. Causa: "
				+ e.getMessage(), e);
		}
		return resultado;
	}

	@Override
	public Boolean recriarBancoDados() throws DAOException {
		Boolean resultado = true;
		try {
			iniciarTransacao();
			Statement sstmt = conexao.createStatement();
			String sql01 = "DROP TABLE Lances ";
			sstmt.execute(sql01);

			String sql02 = "DROP TABLE Leiloes ";
			sstmt.execute(sql02);

			String sql03 = "DROP TABLE Bens ";
			sstmt.execute(sql03);

			String sql04 = "DROP TABLE Categorias ";
			sstmt.execute(sql04);

			String sql05 = "DROP TABLE Usuarios ";
			sstmt.execute(sql05);

			criarBancoDados();

			finalizarTransacao();
		} catch (SQLException ex) {
			Logger.getLogger(UtilDAODerby.class
				.getName()).log(Level.SEVERE, null, ex);
		}
		return resultado;
	}

	private void foreach() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
