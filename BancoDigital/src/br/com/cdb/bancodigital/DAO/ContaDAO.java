package br.com.cdb.bancodigital.DAO;

import br.com.cdb.bancodigital.entity.*;

public class ContaDAO {

	protected ClienteDAO clienteDAO;
	protected Cliente cliente;

	public ContaDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public Conta buscarConta(Conta conta) {
		for (Cliente cliente : clienteDAO.getListaDeClientes()) {
			for (Conta contas : cliente.getTodasAsContas()) {
				if (contas.getID().equals(conta.getID())) {
					System.out.println(contas);
					return contas;
				}
			}
		}
		return null;
	}

	public void listarContas() {
		for (Cliente cliente : clienteDAO.getListaDeClientes()) {
			System.out.println(cliente);
			if (cliente != null && cliente.getTodasAsContas() != null) {
				for (Conta conta : cliente.getTodasAsContas()) {
					System.out.println(" ======  LISTA DE CONTAS:  ======");
					System.out.println(conta);
				}
			} else {
				System.out.println("Cliente ou lista de clientes está nula");
			}
		}

	}

}