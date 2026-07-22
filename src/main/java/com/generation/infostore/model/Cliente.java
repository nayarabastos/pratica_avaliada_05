package com.generation.infostore.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_cliente") 
public class Cliente{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	@NotBlank(message = "O atributo nome é obrigatório!")
	@Size(min = 2, max = 100, message = "O nome deve ter no mínimo 2 e no máximo 100 caracteres")
	@Column(length = 100)
	private String nome;
	
	@NotBlank(message = "O atributo nome é obrigatório!")
	@Size(max = 100, message = "O nome deve ter no mínimo 5 e no máximo 100 caracteres")
	@Column(length = 100)
	@Email(message = "Formato de e-mail inválido.")
	private String email;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties(value = "cliente", allowSetters = true)
	private List<Pedido> pedido;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}
	
	
	
	
}
