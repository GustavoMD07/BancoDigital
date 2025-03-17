package br.com.cdb.bancodigital.entity;

import java.util.concurrent.TimeUnit;

public class ContaCorrente extends Conta {

	private static final double taxa = 10.00; // taxa mensal

	public ContaCorrente(String titular, int intervalo, TimeUnit tempo) {
		super(titular, intervalo, tempo);
		tipoDeConta = "Conta Corrente\n";
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
		if (valor > saldo) {
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
		if (saldo <= taxa) {
			System.out.println("Não foi possível cobrar a taxa, saldo insuficiente");
		} else {
			saldo -= taxa;
			System.out.println("Taxa mensal de R$ " + taxa + " aplicada à conta corrente.");
			System.out.println("Novo saldo: R$ " + saldo);
		}
	}

}
