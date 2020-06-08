package com.provainter.exceptions;

/**
 * @author Cesar
 * @see com.provainter
 * @since 07/06/2020
 */
public class UsuarioNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1959789731968848662L;

	public UsuarioNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
