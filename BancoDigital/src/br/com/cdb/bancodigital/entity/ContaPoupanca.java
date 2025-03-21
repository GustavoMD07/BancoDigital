package br.com.cdb.bancodigital.entity;

public class ContaPoupanca extends Conta { // taxa de rendimento = 0,672%

	public ContaPoupanca(Cliente cliente) {
		super(cliente);
		tipoDeConta = "Conta Poupança";
	}

	@Override
	public void depositar(double valor) {
		if (valor < 0) {
			System.out.println("Não é possível inserir um valor negativo na conta poupança");
		} else {
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

		if (valor > saldo && valor < 0) {
			System.out.println("Não foi possível realizar a operação. Saldo bancário insuficiente");
		} else {
			retirarSaldo(valor);
			System.out.println("Seu saque de R$ " + valor + " Foi concluído com êxito");
		}

	}

	@Override
	public boolean transferencia(double valor, Conta conta) {
		if (saldo >= valor || valor > 0) {
			conta.addSaldo(valor);
			System.out.println("Transferência de: R$ " + valor + " concluída");
			saldo -= valor;
			return true;
		}

		else {
			System.out.println("Não foi possível realizar a operação, saldo insuficiente da conta de origem");
			return false;
		}
	}

	@Override
	public void taxa() {

		if (cliente == null) {
			System.out.println("Cliente não encontrado");
			return;
		}

		double taxa = 0;

		if (cliente instanceof ClienteComum) {
			taxa = 0.005; // 5%
		}

		else if (cliente instanceof ClienteSuper) {
			taxa = 0.007;
		}

		else if (cliente instanceof ClientePremium) {
			taxa = 0.009;
		}

		if (saldo <= 0) {
			System.out.println("Não foi possível render ");
		} else {
			saldo += saldo * taxa;
			System.out.println("Rendimento mensal aplicado à conta poupança.");
			System.out.println("Novo saldo: R$ " + saldo);
		}
	}

}
