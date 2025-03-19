package br.com.cdb.bancodigital.entity;

import java.time.LocalDate;

public class ClienteSuper extends Cliente {

	public ClienteSuper(String nome, String cpf, LocalDate dataNascimento) {
		super(nome, cpf, dataNascimento);
		tipoDeCliente = "Cliente Super";
	}
}
