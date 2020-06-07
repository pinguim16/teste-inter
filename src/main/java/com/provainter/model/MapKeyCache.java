package com.provainter.model;

import java.security.PrivateKey;
import java.util.Objects;

/**
 * @author Cesar
 * @see com.provainter.model
 * @since 07/06/2020
 */
public class MapKeyCache {

    private Long idUsuario;

    private PrivateKey privateKey;

    public MapKeyCache(Long idUsuario, PrivateKey privateKey) {
        this.idUsuario = idUsuario;
        this.privateKey = privateKey;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapKeyCache that = (MapKeyCache) o;
        return Objects.equals(idUsuario, that.idUsuario) &&
                Objects.equals(privateKey, that.privateKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, privateKey);
    }
}
