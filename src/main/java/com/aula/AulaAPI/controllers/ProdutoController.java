package com.aula.AulaAPI.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aula.AulaAPI.entidades.Produto;
import com.aula.AulaAPI.mocks.ProdutoMock;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class ProdutoController {

    @GetMapping("/produtos")
    public List<Produto> listarProdutos() {
        return ProdutoMock.getProdutos();
    }

    @GetMapping("/produtos/{id}")
    public ResponseEntity<?> buscarProduto(@PathVariable Long id) {

        for (Produto produto : ProdutoMock.getProdutos()) {

            if (produto.getId().equals(id)) {
                return ResponseEntity.ok(produto);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/produtos")
    public ResponseEntity<?> cadastrarProduto(@RequestBody Produto produto) {

        Long novoId = 1L;

        for (Produto p : ProdutoMock.getProdutos()) {

            if (p.getId() >= novoId) {
                novoId = p.getId() + 1;
            }
        }

        produto.setId(novoId);

        ProdutoMock.getProdutos().add(produto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @PutMapping("/produtos/{id}")
    public ResponseEntity<?> editarProduto(
            @PathVariable Long id,
            @RequestBody Produto produtoAtualizado) {

        for (Produto produto : ProdutoMock.getProdutos()) {

            if (produto.getId().equals(id)) {

                produto.setDescricao(produtoAtualizado.getDescricao());
                produto.setPreco(produtoAtualizado.getPreco());
                produto.setEstoque(produtoAtualizado.getEstoque());

                return ResponseEntity.ok(produto);
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/produtos/{id}")
    public ResponseEntity<?> excluirProduto(@PathVariable Long id) {

        boolean removido = ProdutoMock.getProdutos()
                .removeIf(produto -> produto.getId().equals(id));

        if (removido) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}