package com.provainter.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * @author Cesar
 * @see com.provainter.resource
 * @since 05/06/2020
 */

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Column(name = "email")
    private String email;

    @Column(name = "dados_criptografados")
    private byte[] dadosCriptografados;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Set<Digito> digitosUnicos;

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

    public Set<Digito> getDigitosUnicos() {
        return digitosUnicos;
    }

    public void setDigitosUnicos(Set<Digito> digitosUnicos) {
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
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) &&
                Objects.equals(nome, usuario.nome) &&
                Objects.equals(email, usuario.email) &&
                Objects.equals(digitosUnicos, usuario.digitosUnicos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, digitosUnicos);
    }
}
