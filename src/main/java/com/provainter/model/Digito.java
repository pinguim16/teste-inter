package com.provainter.model;

import javax.persistence.*;

/**
 * @author Cesar
 * @see com.provainter.resource
 * @since 05/06/2020
 */

@Entity
@Table(name = "digito")
public class Digito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "entrada_n")
    private String entradaN;

    @Column(name = "entrada_k")
    private Integer entradaK;

    @Column(name = "concat_p")
    private String concatP;

    @Column(name = "resultado_digito_unico")
    private Integer resultadoDigitoUnico;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
