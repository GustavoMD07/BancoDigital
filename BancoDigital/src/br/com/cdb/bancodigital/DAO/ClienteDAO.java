package br.com.cdb.bancodigital.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.cdb.bancodigital.entity.*;

public class ClienteDAO {
	
	private ArrayList<Cliente> listaDeClientes = new ArrayList<>();
	
	

	public void addCliente(Cliente cliente) {
		if(buscarCliente(cliente.getCpf()) != null) {
			System.out.println("Erro!. Já existe um cliente com esse cpf.");
			return; //verificando se já tem um cliente com o cpf, se sim, ele sai desse código
		}
		
		System.out.println(cliente + "\n Cliente adicionado\n");
		listaDeClientes.add(cliente);
	}
	
	public void removerCliente(Cliente cliente) {
		System.out.println(cliente + "\n Cliente removido");
		listaDeClientes.remove(cliente);
	}
	
	public void addConta(Cliente cliente, Conta conta) {
		cliente = buscarCliente(cliente.getCpf());
		if(cliente == null) {
			System.out.println("Cliente não encontrado!");
			return;
		}
		cliente.adicionarConta(conta);
	}
	
	public void removerConta(Cliente cliente, Conta conta) {
		cliente = buscarCliente(cliente.getCpf());
		if(cliente == null) {
			System.out.println("Cliente não encontrado");
			return;
		}
		cliente.removerConta(conta);
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
	
	public List<Cliente> listarClientes() {
		return new ArrayList<>(listaDeClientes); //cópia da lista pra evitar modificações ou erros
	}
	
	public ArrayList<Cliente> getListaDeClientes() {
		return listaDeClientes;
	}
	
	
}