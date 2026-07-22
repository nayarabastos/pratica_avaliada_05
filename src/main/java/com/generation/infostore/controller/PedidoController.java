package com.generation.infostore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.infostore.model.Pedido;
import com.generation.infostore.repository.PedidoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PedidoController {
	@Autowired
	private PedidoRepository pedidoRepository;

	@GetMapping

	public ResponseEntity<List<Pedido>> getAll() {

		return ResponseEntity.ok(pedidoRepository.findAll());

	}

	@GetMapping("/{id}")

	public ResponseEntity<Pedido> getById(@PathVariable Long id) {

		return pedidoRepository.findById(id)

				.map(resposta -> ResponseEntity.ok(resposta))

				.orElse(ResponseEntity.notFound().build());

	}
	
	@GetMapping("/produto/{produto}")
	public ResponseEntity<List<Pedido>> getAllByProduto(@PathVariable String produto) {
		return ResponseEntity.ok(pedidoRepository.findAllByProdutoContainingIgnoreCase(produto));
	}
	

	@PostMapping
	public ResponseEntity<Pedido> post(@Valid @RequestBody Pedido Pedido) {
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidoRepository.save(Pedido));

	}

	@PutMapping
	public ResponseEntity<Pedido> put(@Valid @RequestBody Pedido Pedido) {

		if (pedidoRepository.existsById(Pedido.getId()))
			return ResponseEntity.ok(pedidoRepository.save(Pedido));

		return ResponseEntity.notFound().build();
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {

		Optional<Pedido> Pedido = pedidoRepository.findById(id);

		if (Pedido.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		pedidoRepository.deleteById(id);
	}
}
