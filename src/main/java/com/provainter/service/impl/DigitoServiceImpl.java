package com.provainter.service.impl;

import com.provainter.mapper.DigitoMapper;
import com.provainter.model.Digito;
import com.provainter.model.dto.DigitoDTO;
import com.provainter.service.CacheService;
import com.provainter.service.DigitoService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author Cesar
 * @see com.provainter.service.impl
 * @since 06/06/2020
 */
@Service
public class DigitoServiceImpl implements DigitoService {

    private static final int DECIMAL = 10;

    @Autowired
    private CacheService cacheService;

    @Override
    public Integer digitoUnico(Integer entradaK, String entradaN) {

        DigitoDTO digitoDTO = this.cacheService.getDigitoDTO(entradaN, entradaK);
        if(digitoDTO == null) {
            Digito digito = new Digito();
            digito.setEntradaK(entradaK);
            digito.setEntradaN(entradaN);
            digito.setConcatP(this.cocatenacaoDigitos(entradaK, entradaN));

            Integer resultado = this.somaDigitos(digito.getConcatP());
            while (resultado >= DECIMAL) {
                resultado = this.somaDigitos(resultado.toString());
            }
            digito.setResultadoDigitoUnico(resultado);
            digitoDTO = DigitoMapper.INSTANCE.digitoToDigitoDTO(digito);
            this.cacheService.adicionaDigitoCache(digitoDTO,entradaN, entradaK);
        }
        return digitoDTO.getResultadoDigitoUnico();
    }

    @Override
    public String cocatenacaoDigitos(Integer entradaK, String entradaN) {
        return String.join(Strings.EMPTY, Collections.nCopies(entradaK, entradaN));
    }

    @Override
    public Integer somaDigitos(String digitosConcatenados) {
        return digitosConcatenados.chars().map(c -> Character.digit(c, DECIMAL)).sum();
    }
}
