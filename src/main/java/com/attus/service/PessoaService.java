package com.attus.service;

import com.attus.dto.PessoaDTO;
import com.attus.entity.Endereco;
import com.attus.entity.Pessoa;
import com.attus.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public void criarPessoa(String nome, LocalDate dataNascimento) {
        Pessoa pessoa = new Pessoa(nome, dataNascimento);
        pessoaRepository.save(pessoa);

    }

    public List<PessoaDTO> listarPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll(); // Assumindo que você tenha um repository para acesso ao banco de dados
        return pessoas.stream()
                .map(pessoa -> new PessoaDTO(pessoa.getNomeCompleto(), pessoa.getDataNascimento()))
                .collect(Collectors.toList());
    }


    public void editarPessoa(Long id, String nomeAtualizado, LocalDate pessoaAtualizada) {
        Pessoa pessoaExistente = pessoaRepository.findById(id).orElse(null);
        if (pessoaExistente != null) {
            pessoaExistente.setNomeCompleto(nomeAtualizado);
            pessoaExistente.setDataNascimento(pessoaAtualizada);
            pessoaRepository.save(pessoaExistente);
        } else {
            throw new IllegalArgumentException("Pessoa não encontrada com o ID fornecido: " + id);
        }
    }

    public Pessoa consultarPessoa(Long id) {
        return pessoaRepository.findById(id).orElse(null);
    }

    public void adicionarEndereco(Long idPessoa, Endereco endereco) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElse(null);
        if (pessoa != null) {
            pessoa.adicionarEndereco(endereco);
            pessoaRepository.save(pessoa);
        } else {
            throw new IllegalArgumentException("Pessoa não encontrada com o ID fornecido: " + idPessoa);
        }
    }

    public void definirEnderecoPrincipal(Long idPessoa, Long idEndereco) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElse(null);
        if (pessoa != null) {
            for (Endereco endereco : pessoa.listarEnderecos()) {
                if (endereco.getId().equals(idEndereco)) {
                    endereco.setPrincipal(true);
                } else {
                    endereco.setPrincipal(false);
                }
            }
            pessoaRepository.save(pessoa);
        } else {
            throw new IllegalArgumentException("Pessoa não encontrada com o ID fornecido: " + idPessoa);
        }
    }

}
