package br.com.cdb.bancodigital.DAO;

import java.util.ArrayList;

import br.com.cdb.bancodigital.entity.*;

public class ClienteDAO {
	
	 protected ArrayList<Cliente> listaDeClientes = new ArrayList<>();
	
	public ArrayList<Cliente> getListaDeClientes() {
		return listaDeClientes;
	}

	public void addCliente(Cliente cliente) {
		System.out.println(cliente + "\n Cliente adicionado\n");
		listaDeClientes.add(cliente);
	}
	
	public Cliente buscarCliente(String cpf) {
		for(Cliente c : listaDeClientes) {
			if(c.getCpf().equals(cpf)) {
				return c;
			}
		}
		System.out.println("Cliente não encontrado");
		return null;
	}
	
	public void listarClientes() {
		for(Cliente c : listaDeClientes) {
			System.out.println(c);
		}
	}
	
	public void addConta(Cliente cliente, Conta conta) {
		cliente = buscarCliente(cliente.getCpf());
		if(cliente != null) {
			cliente.adicionarConta(conta);
		}
		
		else { System.out.println("Cliente não encontrado!"); }
	}
}
