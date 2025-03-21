package br.com.cdb.bancodigital.DAO;

import java.util.ArrayList;

import br.com.cdb.bancodigital.entity.*;

public class ClienteDAO {
	
	private ArrayList<Cliente> listaDeClientes = new ArrayList<>();
	
	

	public void addCliente(Cliente cliente) {
		if(buscarCliente(cliente.getCpf()) != null) {
			System.out.println("Erro!. Já existe um cliente com esse cpf.");
			return; //verificando se já tem um cliente com o cpf, se sim, ele sai desse código
		}
		
		listaDeClientes.add(cliente);
//		System.out.println("Cliente adicionado na lista: " + listaDeClientes);
	}
	
	public void removerCliente(Cliente cliente) {
		System.out.println(cliente + "\n Cliente removido");
		listaDeClientes.remove(cliente);
	}
	
	
	public Cliente buscarCliente(String cpf) {
		for(Cliente c : listaDeClientes) {
			if(c.getCpf().equals(cpf)) {
				return c;
			}
		}
		return null;
	}
	
	public ArrayList<Cliente> getListaDeClientes() {
		return listaDeClientes;
	}
	
	
	
	
}