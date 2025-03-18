package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.entity.Conta;
import br.com.cdb.bancodigital.DAO.ContaDAO;
import java.util.ArrayList;
import java.util.List;

public class ContaService {

	private ContaDAO contaDAO;
	
	public ContaService(ContaDAO contaDAO) {
		this.contaDAO = contaDAO;
	}
	
	public boolean transferencia(double valor, Conta origem, Conta destino) {
		if(origem == null || destino == null ) {
			System.out.println("Erro. Conta de origem ou destino inválidas");
			return false;
		}
		
		if(origem.equals(destino)) {
			System.out.println("Não é possível transferir para a própria conta, infelizmente...");
			return false;
		}
		
		return origem.transferencia(valor, destino);
	}

	public void depositar(double valor, Conta conta) {
		if(conta == null) {
			System.out.println("Erro. Conta não encontrada / inválida");
			return;
		}
		conta.depositar(valor);
	}
	
	
	public void sacar(double valor, Conta conta) {
		if (conta == null) {
			System.out.println("Erro. Conta não encontrada / inválida");
			return;
		}
		 conta.saque(valor);
	}
	
	public List<Conta> buscarContaPorTitular(String titular) {
		System.out.println("Contas do titular:");
		return contaDAO.buscarContasPorTitular(titular);
	}
	
	public Conta buscarContaPorId(int idConta) {
	    Conta conta = contaDAO.buscarContaPorId(idConta);
	    if (conta == null) {
	        System.out.println("Erro. Conta não encontrada para o ID: " + idConta);
	    }
	    return conta;
	}
	
	

}
