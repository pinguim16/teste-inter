package com.provainter.model.dto;

import com.provainter.model.Usuario;

/**
 * @author Cesar
 * @see com.provainter.resource
 * @since 05/06/2020
 */
public class DigitoDTO {

    private Long id;

    private String entradaN;

    private String entradaK;

    private String concatP;

    private Integer resultadoDigitoUnico;

    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntradaN() {
        return entradaN;
    }

    public void setEntradaN(String entradaN) {
        this.entradaN = entradaN;
    }

    public String getEntradaK() {
        return entradaK;
    }

    public void setEntradaK(String entradaK) {
        this.entradaK = entradaK;
    }

    public String getConcatP() {
        return concatP;
    }

    public void setConcatP(String concatP) {
        this.concatP = concatP;
    }

    public Integer getResultadoDigitoUnico() {
        return resultadoDigitoUnico;
    }

    public void setResultadoDigitoUnico(Integer resultadoDigitoUnico) {
        this.resultadoDigitoUnico = resultadoDigitoUnico;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
