package br.com.cdb.bancodigital.DAO;

import java.util.ArrayList;
import java.util.List;

import br.com.cdb.bancodigital.entity.*;

public class ContaDAO {

	private ClienteDAO clienteDAO;
	protected List<ContaPoupanca> listaContaPoupanca;
	protected List<ContaCorrente> listaContaCorrente;
	protected List<Conta> todasAsContas;

	public ContaDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
		this.listaContaCorrente = new ArrayList<>();
		this.listaContaPoupanca = new ArrayList<>();
		this.todasAsContas = new ArrayList<>();
	}

	public void adicionarConta(Cliente cliente, Conta conta) {
	    if (cliente != null) {
	        
	    	if (conta instanceof ContaCorrente) {			
				this.listaContaCorrente.add((ContaCorrente) conta);
				System.out.println("Conta corrente adicionada : " + conta + "\n");
			}
			else if (conta instanceof ContaPoupanca) {
				this.listaContaPoupanca.add((ContaPoupanca) conta);
				System.out.println("Conta poupança adicionada : " + conta + "\n");
			}
	    	
	        if (getTodasAsContas() == null) {
	            setTodasAsContas(new ArrayList<>()); // Inicializa a lista de contas se for nula
	        }

	        getTodasAsContas().add(conta);
	    } else {
	        System.out.println("Erro: Cliente não encontrado.");
	    }
	}
	
	
	public Conta buscarContaPorId(int idConta) {
//	    for (Cliente cliente : clienteDAO.getListaDeClientes()) {
	        for (Conta conta : getTodasAsContas()) {
	            if (conta.getID() == idConta) {
	                return conta;
	            }
	        }
	    
	    return null; // Conta não encontrada
}

	public void listarContas() {
		for (Cliente cliente : clienteDAO.getListaDeClientes()) {
			System.out.println(cliente);
			if (cliente != null && getTodasAsContas() != null) {
				System.out.println(" ======  LISTA DE CONTAS:  ======");
				for (Conta conta : getTodasAsContas()) {
					System.out.println(conta);
				}
			} else {
				System.out.println("Cliente ou lista de clientes está nula");
			}
		}
	}
	
	public List<Conta> getTodasAsContas() {
		todasAsContas.addAll(listaContaCorrente);
		todasAsContas.addAll(listaContaPoupanca);
		return todasAsContas;
	}
	
	public List<ContaPoupanca> getListaContaPoupanca() {
		return listaContaPoupanca;
	}

	public List<ContaCorrente> getListaContaCorrente() {
		return listaContaCorrente;
	}
	
	public void setTodasAsContas(List<Conta> todasAsContas) {
		this.todasAsContas = todasAsContas;
	}
	
//	public List<Conta> buscarContasPorTitular(String titular) {
//	List<Conta> contasEncontradas = new ArrayList<>();
//	for (Cliente cliente : clienteDAO.getListaDeClientes()) {
//		if(cliente.getNome().equalsIgnoreCase(titular)) {
//			contasEncontradas.addAll(getTodasAsContas());
//		}
//	}
//	
//	if(contasEncontradas.isEmpty()) {
//		System.out.println("Erro. Não foi encontrado contas pelo titular: " + titular);
//	}
//	return contasEncontradas;
//}

}