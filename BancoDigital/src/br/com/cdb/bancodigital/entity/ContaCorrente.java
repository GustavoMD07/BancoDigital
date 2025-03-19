package br.com.cdb.bancodigital.entity;

import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.entity.ClienteComum;
import br.com.cdb.bancodigital.entity.ClientePremium;
import br.com.cdb.bancodigital.entity.ClienteSuper;

public class ContaCorrente extends Conta {

	public ContaCorrente(Cliente cliente) {
		super(cliente);
		tipoDeConta = "Conta Corrente";
	}

	@Override
	public void depositar(double valor) {
		{
			if (valor < 0) {
				System.out.println("Não é possível inserir um valor negativo na conta corrente.");
			} else {
				addSaldo(valor);
				System.out.println("Seu depósito de R$ " + valor + " Foi devidamente concluído");
			}
		}
	}

	@Override
	public void saldo() {

		System.out.println("Saldo bancário da conta corrente de: R$ " + saldo);
	}

	@Override
	public boolean saque(double valor) {
		
		if (valor > saldo && valor < 0) {
			System.out.println("Não foi possível realizar a operação. Saldo bancário insuficiente");
			transf = false;
			return false;
		} else {
			retirarSaldo(valor);
			System.out.println("Seu saque de R$ " + valor + " Foi concluído com êxito");
			transf = true;
			return true;
		}

	}

	@Override
	public boolean transferencia(double valor, Conta conta) {
		if (saldo >= valor && valor > 0) {
			conta.addSaldo(valor);
			System.out.println("Transferência de: R$ " + valor + " concluída");
			saldo -= valor;
			return true;
		} else {
			System.out.println("Não foi possível realizar a operação");
			return false;
		}
	}
	
	@Override
	public void taxa() {
		
		if(cliente == null) {
			System.out.println("Cliente não encontrado");
			return;
		}
		
		double taxa = 0;
		
		if (cliente instanceof ClienteComum) {
			taxa = 12.0;
		}
		
		else if(cliente instanceof ClienteSuper) {
			taxa = 8.0;
		}
		
		else if(cliente instanceof ClientePremium) {
			taxa = 0.0;
		}
		
		
		if (saldo <= taxa) {
			System.out.println("Não foi possível cobrar a taxa, saldo insuficiente");
		} else {
			saldo -= taxa;
			System.out.println("Taxa mensal de R$ " + taxa + " descontada à conta corrente.");
			System.out.println("Novo saldo: R$ " + saldo);
		}
	}

}
