package com.attus.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private LocalDate dataNascimento;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Endereco> enderecos;

    @JsonIgnore
    public Pessoa(String nomeCompleto, LocalDate dataNascimento) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
    }

    public Pessoa(Long id, String nomeCompleto, LocalDate dataNascimento, List<Endereco> enderecos) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.enderecos = enderecos;
    }

    public Pessoa() {

    }

    public void adicionarEndereco(Endereco endereco) {
        this.enderecos.add(endereco);
    }


    public List<Endereco> listarEnderecos() {
        return this.enderecos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
}
