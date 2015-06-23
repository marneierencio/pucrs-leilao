/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Marnei
 */
public class NegocioException extends Exception {
    public NegocioException() {
    }

    public NegocioException(String mensagem) {
        super(mensagem);
    }

    public NegocioException(Throwable causa) {
        super(causa);
    }

    public NegocioException(String message, Throwable causa) {
        super(message, causa);
    }

    public NegocioException(String mensagem, Throwable causa,
            boolean enableSuppression, boolean writableStackTrace) {
        super(mensagem, causa, enableSuppression, writableStackTrace);
    }
}
