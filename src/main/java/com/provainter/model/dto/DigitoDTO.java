package com.provainter.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Cesar
 * @see com.provainter.resource
 * @since 05/06/2020
 */
public class DigitoDTO {

    @ApiModelProperty("Id digitoUnico")
    private Long id;

    @ApiModelProperty("EntradaN utilizada para calcular digito unico.")
    private String entradaN;

    @ApiModelProperty("EntradaK utilizada no loop para para calcular digito unico.")
    private Integer entradaK;

    @ApiModelProperty("Contatenação da entradaN para calcular digito unico.")
    private String concatP;

    @ApiModelProperty("Resultado do calculo do digito unico.")
    private Integer resultadoDigitoUnico;

    @JsonIgnore
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
