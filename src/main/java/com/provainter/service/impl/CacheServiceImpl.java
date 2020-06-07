package com.provainter.service.impl;

import com.provainter.model.MapKeyCache;
import com.provainter.model.MapKeyCacheDigito;
import com.provainter.model.dto.DigitoDTO;
import com.provainter.service.CacheService;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Cesar
 * @see com.provainter.service.impl
 * @since 06/06/2020
 */
@Service
public class CacheServiceImpl implements CacheService {

    private static final int CACHE_LIMIT = 10;
    private static final Map<MapKeyCacheDigito, DigitoDTO> CACHE = new LinkedHashMap<>();
    private static final Map<PublicKey, MapKeyCache> CACHE_CRIP = new LinkedHashMap<>();

    @Override
    synchronized public void adicionaDigitoCache(DigitoDTO digitoDTO, String entradaN, Integer entradaK) {
        MapKeyCacheDigito mapKeyCacheDigito = new MapKeyCacheDigito(entradaN, entradaK);
        CACHE.putIfAbsent(mapKeyCacheDigito, digitoDTO);
        this.removeUltimoDigitoCache();
    }

    @Override
    public void removeUltimoDigitoCache() {
        if (CACHE.size() > CACHE_LIMIT) {
            CACHE.remove(CACHE.entrySet().iterator().next().getKey());
        }
    }

    @Override
    public DigitoDTO getDigitoDTO(String entradaN, Integer entradaK) {
        MapKeyCacheDigito mapKeyCacheDigito = new MapKeyCacheDigito(entradaN, entradaK);
        return CACHE.get(mapKeyCacheDigito);
    }

    @Override
    public void adicionaChaveCache(Long idUsuario, PublicKey publicKey, PrivateKey privateKey) {
        MapKeyCache mapKeyCache = new MapKeyCache(idUsuario, privateKey);
        CACHE_CRIP.put(publicKey, mapKeyCache);
    }

    @Override
    public MapKeyCache getPrivateKey(PublicKey publicKey) {
        return CACHE_CRIP.get(publicKey);
    }
}
