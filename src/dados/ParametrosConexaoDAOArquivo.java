/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import negocio.ParametrosConexaoDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.pojos.ParametrosConexao;

/**
 *
 * @author Marnei
 */
public class ParametrosConexaoDAOArquivo implements ParametrosConexaoDAO{
    private static ParametrosConexaoDAOArquivo ref;
    //so renomear se precisar do outro properties
    File prop_file = new File(new File("").getAbsolutePath() + "/jdbc.properties");

    
    public static ParametrosConexaoDAOArquivo getInstance() throws DAOException{
        if (ref == null)
            ref = new ParametrosConexaoDAOArquivo();
        return ref;
    }

    ParametrosConexaoDAOArquivo() throws DAOException {
    
    }
    
    @Override
    public ParametrosConexao recuperar() throws DAOException {
        ParametrosConexao parametrosConexao = new ParametrosConexao();
	try {
	    FileInputStream fis;
	    
	    if (!prop_file.isFile()) {
		try {
		    throw new Exception("Não achou a arquivo de dados da conexão, caminho:" + prop_file.getAbsolutePath() + " , errado.");
		} catch (Exception ex) {
		    Logger.getLogger(ParametrosConexaoDAOArquivo.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
	    
	    Properties properties = new Properties();
	    //Setamos o arquivo que será lido
	    fis = new FileInputStream(prop_file.getAbsolutePath());

	    //método load faz a leitura através do objeto fis
	    properties.load(fis);
	    
	    //Captura o valor da propriedade, através do nome da propriedade(Key)
	    parametrosConexao.setBdServidor(properties.getProperty("jdbc.servidor"));
	    parametrosConexao.setBdPorta(Integer.parseInt(properties.getProperty("jdbc.porta")));
	    parametrosConexao.setBdNome(properties.getProperty("jdbc.bancoDados"));
	    parametrosConexao.setBdUsuario(properties.getProperty("jdbc.usuario"));
	    parametrosConexao.setBdSenha(properties.getProperty("jdbc.senha"));

	} catch (FileNotFoundException ex) {
	    Logger.getLogger(ParametrosConexaoDAOArquivo.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(ParametrosConexaoDAOArquivo.class.getName()).log(Level.SEVERE, null, ex);
	}
	return parametrosConexao;
    }

    @Override
    public ParametrosConexao atualizar(ParametrosConexao parametrosConexao) throws DAOException {
	try {
	    Properties properties = new Properties();
	    properties.setProperty("jdbc.servidor", parametrosConexao.getBdServidor());
	    properties.setProperty("jdbc.porta", parametrosConexao.getBdPorta().toString());
	    properties.setProperty("jdbc.bancoDados", parametrosConexao.getBdNome());
	    properties.setProperty("jdbc.usuario", parametrosConexao.getBdUsuario());
	    properties.setProperty("jdbc.senha", parametrosConexao.getBdSenha());
	    
	    FileOutputStream fos = new FileOutputStream(prop_file);
	    properties.store(fos, "parametros de conexao com banco de dados");
	    fos.close();
	} catch (FileNotFoundException ex) {
	    Logger.getLogger(ParametrosConexaoDAOArquivo.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(ParametrosConexaoDAOArquivo.class.getName()).log(Level.SEVERE, null, ex);
	}
	return parametrosConexao;
    }
}
