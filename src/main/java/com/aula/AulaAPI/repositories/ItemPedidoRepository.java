package com.aula.AulaAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aula.AulaAPI.entidades.ItemPedido;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

}