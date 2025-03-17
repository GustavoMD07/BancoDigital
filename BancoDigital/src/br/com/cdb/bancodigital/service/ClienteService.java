package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.DAO.ClienteDAO;
import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.entity.Conta;
import java.util.List;

public class ClienteService {

	private List <Cliente> clientes;
	
	public ClienteService(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
	public void adicionarCliente(Cliente cliente) {
		if (cliente == null) {
			System.out.println("Cliente não encontrado / inválido");
			return;
		}
		clientes.add(cliente);
		System.out.println("Cliente: " + cliente + " adicionado com sucesso");
	}

	public Cliente buscarClientePorNome(String nome) {
		for( Cliente cliente : clientes ) {
			if(cliente.getNome().equalsIgnoreCase(nome)) {
				return cliente;
			}
		}
		System.out.println("Cliente não encontrado.");
		return null;
	}
	
	public void listarClientes() {
		if (clientes.isEmpty()) {
			System.out.println("Não vejo nenhum cliente...");
			return;
		}
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
	}







}
