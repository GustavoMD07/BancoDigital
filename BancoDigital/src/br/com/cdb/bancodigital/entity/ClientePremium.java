package br.com.cdb.bancodigital.entity;

import java.time.LocalDate;

public class ClientePremium extends Cliente {

	public ClientePremium(String nome, String cpf, LocalDate dataNascimento) {
		super(nome, cpf, dataNascimento);
		tipoDeCliente = "Cliente Premium";
	}
	
}
