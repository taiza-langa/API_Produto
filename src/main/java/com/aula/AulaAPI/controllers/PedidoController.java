package com.aula.AulaAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aula.AulaAPI.entidades.ItemPedido;
import com.aula.AulaAPI.entidades.Pedido;
import com.aula.AulaAPI.entidades.Produto;
import com.aula.AulaAPI.repositories.ItemPedidoRepository;
import com.aula.AulaAPI.repositories.PedidoRepository;
import com.aula.AulaAPI.repositories.ProdutoRepository;

@RestController
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @PostMapping("/pedidos")
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido novoPedido = pedidoRepository.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoPedido);
    }

    @PostMapping("/pedidos/{pedidoId}/itens")
    public ResponseEntity<?> adicionarItem(
            @PathVariable Long pedidoId,
            @RequestBody ItemPedido item) {

        Pedido pedido = pedidoRepository.findById(pedidoId).orElse(null);

        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        Long produtoId = item.getProduto().getId();

        Produto produto = produtoRepository.findById(produtoId).orElse(null);

        if (produto == null) {
            return ResponseEntity.notFound().build();
        }

        item.setProduto(produto);
        item.setPedido(pedido);

        pedido.getItens().add(item);

        pedidoRepository.save(pedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }

    @DeleteMapping("/pedidos/{pedidoId}/itens/{itemId}")
    public ResponseEntity<?> excluirItem(
            @PathVariable Long pedidoId,
            @PathVariable Long itemId) {

        Pedido pedido = pedidoRepository.findById(pedidoId).orElse(null);

        if (pedido == null) {
            return ResponseEntity.notFound().build();
        }

        ItemPedido item = itemPedidoRepository.findById(itemId).orElse(null);

        if (item == null) {
            return ResponseEntity.notFound().build();
        }

        pedido.getItens().remove(item);
        pedidoRepository.save(pedido);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pedidos")
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
}