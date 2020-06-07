package com.provainter.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;


/**
 * @author Cesar
 * @see com.provainter
 * @since 05/06/2020
 */
public final class HeaderUtil {

    private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

    private static final String NOME_APLICACAO = "prova-inter";

    private HeaderUtil() {
    }

    public static HttpHeaders criacaoAlerta(String mensagem, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-serviceApp-alerta", mensagem);
        headers.add("X-serviceApp-params", param);
        return headers;
    }

    public static HttpHeaders criacaoAlertaParaEntidadeCriada(String nomeEntidade, String param) {
        return criacaoAlerta(NOME_APLICACAO + "." + nomeEntidade + ".criada", param);
    }

    public static HttpHeaders criacaoAlertaParaEntidadeAtualizada(String nomeEntidade, String param) {
        return criacaoAlerta(NOME_APLICACAO + "." + nomeEntidade + ".atualizada", param);
    }

    public static HttpHeaders criacaoAlertaParaEntidadeDeletada(String nomeEntidade, String param) {
        return criacaoAlerta(NOME_APLICACAO + "." + nomeEntidade + ".deletada", param);
    }

    public static HttpHeaders criacaoAlertaFalha(String nomeEntidade, String chaveErro, String mensagemPadrao) {
        log.error("Falha no processamento da entidade, {}", mensagemPadrao);
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-serviceApp-error", "erro." + chaveErro);
        headers.add("X-serviceApp-params", nomeEntidade);
        return headers;
    }
}
