package br.com.cdb.bancodigital.entity;

public class ClienteSuper extends Cliente {

	public ClienteSuper(String nome, String cpf) {
		super(nome, cpf);
		tipoDeCliente = "Cliente Super\n";
	}
}
