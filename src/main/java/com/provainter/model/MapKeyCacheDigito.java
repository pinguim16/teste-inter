package com.provainter.model;

import java.util.Objects;

/**
 * @author Cesar
 * @see com.provainter.model
 * @since 06/06/2020
 */
public class MapKeyCacheDigito {

    private String entradaN;

    private Integer entradaK;

    public MapKeyCacheDigito(String entradaN, Integer entradaK) {
        this.entradaN = entradaN;
        this.entradaK = entradaK;
    }

    public String getEntradaN() {
        return entradaN;
    }

    public void setEntradaN(String entradaN) {
        this.entradaN = entradaN;
    }

    public Integer getEntradaK() {
        return entradaK;
    }

    public void setEntradaK(Integer entradaK) {
        this.entradaK = entradaK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapKeyCacheDigito that = (MapKeyCacheDigito) o;
        return Objects.equals(entradaN, that.entradaN) &&
                Objects.equals(entradaK, that.entradaK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(entradaN, entradaK);
    }
}
