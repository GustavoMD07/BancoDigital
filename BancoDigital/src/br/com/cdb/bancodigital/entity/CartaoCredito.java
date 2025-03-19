package br.com.cdb.bancodigital.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.entity.ClienteComum;
import br.com.cdb.bancodigital.entity.ClienteSuper;
import br.com.cdb.bancodigital.entity.ClientePremium;


public class CartaoCredito extends Cartao {
	
	protected double limiteCredito;
	protected double saldoDevedor;
	protected double gastoMensal;
	private LocalDate mesAtual;

	public CartaoCredito(String senha, String numCartao, Conta conta, Cliente cliente) {
		super(senha, numCartao, conta);
		this.mesAtual = LocalDate.now();
		tipoDeCartao = "Cartão de crédito";
		
		if(cliente instanceof ClienteComum) {
			this.limiteCredito = 1000;
		}
		else if(cliente instanceof ClienteSuper) {
			this.limiteCredito = 5000;
		}
		else if(cliente instanceof ClientePremium) {
			this.limiteCredito = 10000;
		}
	}

	@Override
	public void aplicarTaxas() {
		
		double exceder = 0.8 * limiteCredito; //80% do limite gasto
		
		if (saldoDevedor > 0 && saldoDevedor >= exceder) {
			double taxa = saldoDevedor * 0.05; //5% se a pessoa não pagou a taxa
			saldoDevedor += taxa;
			System.out.println("Taxa de: R$ " + taxa + " aplicada ao saldo devedor");
			System.out.println("Dica de ouro: pague a fatura do seu cartão de crédito.");
		} else {
			System.out.println("Condições para taxas não aplicáveis");
		}
	}

	@Override
	public void realizarPagamento(double valor) {
		if (!status) {
			System.out.println("Seu cartão está desativado. Ative-o para continuar");
			return; // usando return pra ele já sair do código, sem ir pro if debaixo.
			// se o cartão está desativado, você não precisa verificar o resto.
		}

		verificarMes(); // verificando se o mês mudou e a fatura redefiniu

		if (saldoDevedor + valor > limiteCredito) { // pra não deixar ele passar do limite
			System.out.println("Pagamento negado. Limite de crédito atingido");
		} else {

			saldoDevedor += valor;
			gastoMensal += valor;
			System.out.println("Pagamento de R$: " + valor + " realizado com sucesso");
			System.out.println("Saldo devedor atual: R$" + saldoDevedor);

		}
	}

	public void pagarFatura(double valor) {

		if (saldoDevedor <= 0) {
		    System.out.println("A fatura já está paga!");
		    return;
		}
		if (valor < 0) {
			System.out.println("Infelizmente, não é possível pagar a fatura com um valor abaixo ou igual a 0!.");
		}

		if (valor > saldoDevedor) {
			System.out.println("Valor excedendo o saldo devedor. Valor redefinido para pagar o necessário :)");
			valor = saldoDevedor;
		}

		if (conta.getSaldo() >= valor) {
			conta.retirarSaldo(valor);
			saldoDevedor -= valor;
			System.out.println("Fatura paga no valor de: R$ " + valor);
			System.out.println("Novo saldo devedor: R$ " + saldoDevedor);
		} else {
			System.out.println("Saldo insuficiente para o pagamento da fatura :(");
		}

	}

	private void verificarMes() {

		LocalDate hoje = LocalDate.now();
		if (!hoje.getMonth().equals(mesAtual.getMonth())) {
			gastoMensal = 0;
			mesAtual = hoje;
			System.out.println("Novo mês detectado. Gastos mensais resetados.");
		}
	}

	public void verificarCredito() {
		System.out.println("Saldo devedor: R$ " + saldoDevedor + " || Limite do crédito: " + limiteCredito);
		System.out.println("Você pode gastar mais: R$" + (limiteCredito - saldoDevedor));
	}

	public void setLimiteCredito(double limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

	public double getSaldoDevedor() {
		return saldoDevedor;
	}

	public double getLimiteCredito() {
		return limiteCredito;
	}
	
}
