package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.DAO.ClienteDAO;
import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.entity.Conta;
import java.util.ArrayList;
import java.util.List;

public class ClienteService {

	private ClienteDAO clienteDAO;

	public ClienteService(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public void adicionarCliente(Cliente cliente) {
		if (cliente == null) {
			System.out.println("Cliente não encontrado / inválido");
			return;
		}

		if(clienteDAO.buscarCliente(cliente.getCpf()) != null) {
			System.out.println("Erro!. Já há um cliente cadastrado com esse cpf.");
		}

		clienteDAO.addCliente(cliente);
		System.out.println("Cliente: " + cliente.getNome() + " adicionado com sucesso");
	}

	
	public Cliente buscarClientePorNome(String nome) {
		List<Cliente> clientes =clienteDAO.getListaDeClientes();
		
		for (Cliente cliente : clientes) {
			if (cliente.getNome().equalsIgnoreCase(nome)) {
				return cliente;
			}
		}
		System.out.println("Cliente não encontrado");
		return null;
	}

	
	
	public void listarClientes() {
		List<Cliente> clientes = clienteDAO.getListaDeClientes();
		
		if (clientes.isEmpty()) {
			System.out.println("Não vejo nenhum cliente...");
			return;
		}
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
	}
}
