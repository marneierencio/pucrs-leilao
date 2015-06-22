/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

/**
 *
 * @author Marnei
 */
class GuiException extends Exception {
    public GuiException() {
    }

    public GuiException(String mensagem) {
        super(mensagem);
    }

    public GuiException(Throwable causa) {
        super(causa);
    }

    public GuiException(String message, Throwable causa) {
        super(message, causa);
    }

    public GuiException(String mensagem, Throwable causa,
            boolean enableSuppression, boolean writableStackTrace) {
        super(mensagem, causa, enableSuppression, writableStackTrace);
    }
}
