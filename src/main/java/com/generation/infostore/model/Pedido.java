package com.generation.infostore.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_pedido") 
public class Pedido {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@NotBlank(message = "O atributo nome é obrigatório!")
	@Size(min = 2, max = 100, message = "O nome deve ter no mínimo 5 e no máximo 100 caracteres")
	@Column(length = 100)
	private String produto;
	
	@NotBlank(message = "O atributo descrição é obrigatório")
	@Size(min = 2, max = 100, message = "O descrição deve ter no mínimo 5 e no máximo 100 caracteres")
	@Column(length = 100)
	private String descricao;
	
	@NotNull
	@Positive(message = "O valor precisa ser maior que 0!")
	@Column(precision = 10, scale = 2)
    private BigDecimal valor;
	
	@UpdateTimestamp 
	private LocalDateTime data;
	
	@ManyToOne
	@JsonIgnoreProperties("pedido")
	private Cliente cliente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
