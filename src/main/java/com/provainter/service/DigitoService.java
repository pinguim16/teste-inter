package com.provainter.service;

import com.provainter.model.dto.UsuarioDTO;

/**
 * @author Cesar
 * @see com.provainter.resource
 * @since 05/06/2020
 */
public interface DigitoService {

    /**
     * Responsável por calcular digito e preenchimento do objeto
     *
     * @param entradaN - string	representado um	inteiro. 1<=n<=10ˆ1000000
     * @param entradaK - número	de	vezes da concatenação
     * @return Integer com a soma do digitos
     */
    Integer digitoUnico(Integer entradaK, String entradaN, UsuarioDTO usuario);

    /**
     * Responsável por gerar a string p parae cáculo
     *
     * @param entradaN - string	representado um	inteiro. 1<=n<=10ˆ1000000
     * @param entradaK - número	de	vezes da concatenação
     * @return Integer com a soma do digitos
     */
    String cocatenacaoDigitos(Integer entradaK, String entradaN);

    /**
     * Responsável por calcular a contaneção dos digitos
     *
     * @param digitosConcatenados - concatenação dos digitos enviados pra calculo
     * @return
     */
    Integer somaDigitos(String digitosConcatenados);

}
