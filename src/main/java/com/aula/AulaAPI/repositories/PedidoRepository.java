package com.aula.AulaAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aula.AulaAPI.entidades.Pedido;
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
