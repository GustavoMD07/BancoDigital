package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.entity.Conta;
import java.util.List;

public class ContaService {

	private List<Conta> contas;
	
	public ContaService(List<Conta> contas) {
		this.contas = contas;
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

	public boolean depositar(double valor, Conta conta) {
		if(conta == null) {
			System.out.println("Erro. Conta não encontrada / inválida");
			return false;
		}
		conta.depositar(valor);
		return true;
	}
	public boolean sacar(double valor, Conta conta) {
		if (conta == null) {
			System.out.println("Erro. Conta não encontrada / inválida");
			return false;
		}
		return conta.saque(valor);
	}
	
	public Conta buscarContaPorTitular(String titular) {
		for (Conta conta : contas) {
			if(conta.getTitular().equalsIgnoreCase(titular)) {
				return conta;
			}
		}
		System.out.println("Conta não encontrada para o titular: " + titular);
		return null;
	}












}
