package br.com.cdb.bancodigital.entity;

import java.util.concurrent.TimeUnit;

public class ContaPoupanca extends Conta {		//taxa de rendimento = 0,672%

	private static final double taxa = 0.0672;
	
	public ContaPoupanca(String titular, int intervalo, TimeUnit tempo) {
		super(titular, intervalo, tempo);
		agendarTaxaMensal(intervalo, tempo);
		tipoDeConta = "Conta Poupança\n";
	}

	@Override
	public void depositar(double valor) {
		if (valor < 0) {
			System.out.println("Não é possível inserir um valor negativo na conta poupança");
		}
		else {
			addSaldo(valor);
			System.out.println("Seu depósito de R$ " + valor + " Foi devidamente concluído");
		}
		
	}

	@Override
	public void saldo() {
		System.out.println("Saldo bancário da conta poupança de: R$ " + saldo);
	}

	@Override
	public void saque(double valor) {

		if (valor > saldo) {
			System.out.println("Não foi possível realizar a operação. Saldo bancário insuficiente");
			transf = false;
		}
		else {
			retirarSaldo(valor);
			System.out.println("Seu saque de R$ " + valor + " Foi concluído com êxito");
			transf = true;
		}
		
	}

	@Override
	public void transferencia(double valor, Conta conta) {
		if(saldo >= valor && valor > 0) {
			conta.addSaldo(valor);
			System.out.println("Transferência de: R$ " + valor + " concluída");
			saldo -= valor;
		}
		
		else { System.out.println("Não foi possível realizar a operação"); }
	}

	@Override
	public void taxa() {
		if(saldo <= 0) {
			System.out.println("Não foi possível render ");
		}
		else {
			saldo += saldo * taxa;
			System.out.println("Rendimento diário aplicado à conta poupança.");
			System.out.println("Novo saldo: R$ " + saldo);
		}
	}

}
