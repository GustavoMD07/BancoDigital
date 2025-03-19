package br.com.cdb.bancodigital.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.cdb.bancodigital.entity.*;

public class ContaDAO {

	private ClienteDAO clienteDAO;

	public ContaDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public void adicionarConta(Cliente cliente, Conta conta) {
	    if (cliente != null) {
	        cliente.getTodasAsContas().add(conta);
	        System.out.println("Conta adicionada com sucesso para o cliente: " + cliente.getNome());
	    } else {
	        System.out.println("Erro: Cliente não encontrado.");
	    }
	}
	
	public List<Conta> buscarContasPorTitular(String titular) {
		List<Conta> contasEncontradas = new ArrayList<>();
		for (Cliente cliente : clienteDAO.getListaDeClientes()) {
			if(cliente.getNome().equalsIgnoreCase(titular)) {
				contasEncontradas.addAll(cliente.getTodasAsContas());
			}
		}
		
		if(contasEncontradas.isEmpty()) {
			System.out.println("Erro. Não foi encontrado contas pelo titular: " + titular);
		}
		return contasEncontradas;
	}
	
	public Conta buscarContaPorId(int idConta) {
	    for (Cliente cliente : clienteDAO.getListaDeClientes()) {
	        for (Conta conta : cliente.getTodasAsContas()) {
	            if (conta.getID() == idConta) {
	                return conta;
	            }
	        }
	    }
	    return null; // Conta não encontrada
	}

	public void listarContas() {
		for (Cliente cliente : clienteDAO.getListaDeClientes()) {
			System.out.println(cliente);
			if (cliente != null && cliente.getTodasAsContas() != null) {
				System.out.println(" ======  LISTA DE CONTAS:  ======");
				for (Conta conta : cliente.getTodasAsContas()) {
					System.out.println(conta);
				}
			} else {
				System.out.println("Cliente ou lista de clientes está nula");
			}
		}

	}

}