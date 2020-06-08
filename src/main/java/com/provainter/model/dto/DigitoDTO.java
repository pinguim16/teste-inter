package com.provainter.model.dto;

/**
 * @author Cesar
 * @see com.provainter.resource
 * @since 05/06/2020
 */
public class DigitoDTO {

    private Long id;

    private String entradaN;

    private Integer entradaK;

    private String concatP;

    private Integer resultadoDigitoUnico;

    private UsuarioDTO usuario;

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

    public Integer getEntradaK() {
        return entradaK;
    }

    public void setEntradaK(Integer entradaK) {
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

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
