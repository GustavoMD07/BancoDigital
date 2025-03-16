package br.com.cdb.bancodigital.entity;

public class ClientePremium extends Cliente {

	public ClientePremium(String nome, String cpf) {
		super(nome, cpf);
		tipoDeCliente = "Cliente Premium\n";
	}
	
}
