package br.com.cdb.bancodigital.entity;

public class ClienteComum extends Cliente {
	
	protected Cliente cliente;

	public ClienteComum(String nome, String cpf) {
		super(nome, cpf);
		tipoDeCliente = "Cliente comum\n";
	}

}
