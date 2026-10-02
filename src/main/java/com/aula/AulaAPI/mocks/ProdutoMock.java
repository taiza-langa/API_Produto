package com.aula.AulaAPI.mocks;

import java.util.ArrayList;
import java.util.List;

import com.aula.AulaAPI.entidades.Produto;

public class ProdutoMock {

    private static final List<Produto> produtos = new ArrayList<>();

    static {
        Produto p1 = new Produto();
        p1.setId(1L);
        p1.setDescricao("X-Salada");
        p1.setPreco(25.0);
        p1.setEstoque(10);

        Produto p2 = new Produto();
        p2.setId(2L);
        p2.setDescricao("X-Bacon");
        p2.setPreco(30.0);
        p2.setEstoque(8);

        Produto p3 = new Produto();
        p3.setId(3L);
        p3.setDescricao("Coca-Cola");
        p3.setPreco(7.0);
        p3.setEstoque(20);

        Produto p4 = new Produto();
        p4.setId(4L);
        p4.setDescricao("Batata Frita");
        p4.setPreco(15.0);
        p4.setEstoque(15);

        Produto p5 = new Produto();
        p5.setId(5L);
        p5.setDescricao("X-Tudo");
        p5.setPreco(35.0);
        p5.setEstoque(5);

        produtos.add(p1);
        produtos.add(p2);
        produtos.add(p3);
        produtos.add(p4);
        produtos.add(p5);
    }

    public static List<Produto> getProdutos() {
        return produtos;
    }
}