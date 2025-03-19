package br.com.cdb.bancodigital.entity;

import java.time.LocalDate;

public class ClienteComum extends Cliente {
	
	protected Cliente cliente;

	public ClienteComum(String nome, String cpf, LocalDate dataNascimento) {
		super(nome, cpf, dataNascimento);
		tipoDeCliente = "Cliente Comum";
	}

}
