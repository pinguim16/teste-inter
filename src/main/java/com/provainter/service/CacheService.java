package com.provainter.service;

import com.provainter.model.MapKeyCache;
import com.provainter.model.dto.DigitoDTO;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author Cesar
 * @see com.provainter.service
 * @since 06/06/2020
 */
public interface CacheService {

    void adicionaDigitoCache(DigitoDTO digitoDTO, String entradaN, Integer entradaK);

    void removeUltimoDigitoCache();

    DigitoDTO getDigitoDTO(String entradaN, Integer entradaK);

    void adicionaChaveCache(Long idUsuario, PublicKey publicKey, PrivateKey privateKey);

    MapKeyCache getPrivateKey(PublicKey publicKey);

}
