package com.provainter.model.dto;

import java.util.Objects;
import java.util.Set;

/**
 * @author Cesar
 * @see com.provainter.resource
 * @since 05/06/2020
 */

public class UsuarioDTO {

    private Long id;

    private String nome;

    private String email;

    private Set<DigitoDTO> digitosUnicos;

    private byte[] dadosCriptografados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<DigitoDTO> getDigitosUnicos() {
        return digitosUnicos;
    }

    public void setDigitosUnicos(Set<DigitoDTO> digitosUnicos) {
        this.digitosUnicos = digitosUnicos;
    }

    public byte[] getDadosCriptografados() {
        return dadosCriptografados;
    }

    public void setDadosCriptografados(byte[] dadosCriptografados) {
        this.dadosCriptografados = dadosCriptografados;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioDTO that = (UsuarioDTO) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", digitosUnicos=" + digitosUnicos +
                '}';
    }
}
