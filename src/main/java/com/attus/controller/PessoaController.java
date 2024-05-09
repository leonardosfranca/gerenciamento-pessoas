package com.attus.controller;

import com.attus.dto.PessoaDTO;
import com.attus.entity.Endereco;
import com.attus.entity.Pessoa;
import com.attus.service.PessoaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PessoaController {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }


    @PostMapping
    public ResponseEntity<Void> criarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        pessoaService.criarPessoa(pessoaDTO.nomeCompleto(), pessoaDTO.dataNascimento());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editarPessoa(@PathVariable Long id, @RequestBody PessoaDTO pessoaDTO) {
        pessoaService.editarPessoa(id, pessoaDTO.nomeCompleto());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> consultarPessoa(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.consultarPessoa(id);
        if (pessoa != null) {
            return new ResponseEntity<>(pessoa, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/enderecos")
    public ResponseEntity<Void> adicionarEndereco(@PathVariable Long id, @RequestBody Endereco endereco) {
        pessoaService.adicionarEndereco(id, endereco);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idPessoa}/enderecos/{idEndereco}/principal")
    public ResponseEntity<Void> definirEnderecoPrincipal(@PathVariable Long idPessoa, @PathVariable Long idEndereco) {
        pessoaService.definirEnderecoPrincipal(idPessoa, idEndereco);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
