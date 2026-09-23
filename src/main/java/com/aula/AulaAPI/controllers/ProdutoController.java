package com.aula.AulaAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aula.AulaAPI.entidades.Produto;
import com.aula.AulaAPI.repositories.ProdutoRepository;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping("/produtos")
    public List<Produto> getProdutos() {
        return repository.findAll();
    }

    @PostMapping("/produtos")
    public ResponseEntity<?> save(@RequestBody Produto produto) {
        repository.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }
}
