/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

/**
 *
 * @author Marnei
 */
public class DAOException extends Exception{

    public DAOException() {
    }

    public DAOException(String mensagem) {
        super(mensagem);
    }

    public DAOException(Throwable causa) {
        super(causa);
    }

    public DAOException(String message, Throwable causa) {
        super(message, causa);
    }

    public DAOException(String mensagem, Throwable causa,
            boolean enableSuppression, boolean writableStackTrace) {
        super(mensagem, causa, enableSuppression, writableStackTrace);
    }
}
