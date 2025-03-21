package br.com.cdb.bancodigital.entity;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CartaoDebito extends Cartao {
	
	protected double limiteDiario;
	protected double limiteDiarioInicial;
	private Cartao cartao;
	
	public CartaoDebito(String senha, String numCartao, double limiteDiario, Conta conta, Cliente cliente) {
		super(senha, numCartao, conta, cliente);
		this.limiteDiario = limiteDiario;
		this.limiteDiarioInicial = limiteDiario;
		tipoDeCartao = "Cartão de débito";
		resetarLimiteDiario();
	}

	@Override
	public void aplicarTaxas() {
		double taxaMensal = 5.00;
		
		if (conta.getSaldo() > taxaMensal) {
			conta.retirarSaldo(taxaMensal);
			System.out.println("Taxa mensal de: R$ " + taxaMensal + " aplicada no cartão de débito");
		} else {
			System.out.println("Saldo insuficiente na conta para a aplicação da taxa mensal");
		}
		
	}

	@Override
	public void realizarPagamento(double valor) {
		
		if(valor < 0) {
			System.out.println("Não é possível inserir um valor igual ou menor que 0");
		}
		
		else if(valor > limiteDiario) {
			System.out.println("Pagamento negado! Limite diário excedido, redefina o seu limite"
			+ " ou espere até ser redefinido automaticamente");
		}
		else {
			limiteDiario -= valor;
			System.out.println("Pagamento de: R$ " + valor + " realizado com sucesso");
			System.out.println("Limite diário restante: R$ " + limiteDiario);
		}
	}
	
	
	public void alterarLimiteDiario(double novoLimiteDiario) {
		this.limiteDiario = novoLimiteDiario;
		this.limiteDiarioInicial = novoLimiteDiario;
		System.out.println("Limite diário alterado! ");
		System.out.println("Novo limite: R$ " + this.limiteDiario);
	}
	
	
	
	
	
	
	
	
	
	
	
									//processamento automático, não precisa mexer
	
	private void resetarLimiteDiario() {
		
		ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
		
		long delayInicial = resetMeiaNoite();
        long intervaloDiario = 24 * 60 * 60; // 24 horas em segundos

        scheduler.scheduleAtFixedRate(() -> {
            this.limiteDiario = this.limiteDiarioInicial; // Mantém o limite atual
            System.out.println(" Limite diário redefinido automaticamente às 00h: R$ " + this.limiteDiario);
        }, delayInicial, intervaloDiario, TimeUnit.SECONDS);
        
	}

	private long resetMeiaNoite() {
		
		LocalDateTime agora = LocalDateTime.now();
        LocalDateTime meiaNoite = agora.plusDays(1).truncatedTo(ChronoUnit.DAYS);
        return ChronoUnit.SECONDS.between(agora, meiaNoite);
	}
	
	public void verificarLimiteDiario() {
		System.out.println("Limite diário disponível: R$ " + limiteDiario);
	}
	
	public double getLimiteDiario() {
		return limiteDiario;
	}
	
	protected double getLimiteDiarioInicial() {
		return limiteDiarioInicial;
	}

	protected void setLimiteDiarioInicial(double limiteDiarioInicial) {
		this.limiteDiarioInicial = limiteDiarioInicial;
	}

	protected void setLimiteDiario(double limiteDiario) {
		this.limiteDiario = limiteDiario;
	}
	
	public void infoDebito() {
		
		Cliente cliente = cartao.getCliente(); // preciso pegar o cliente pra querer mostrar ele
		Conta conta = cartao.getConta();
		
		System.out.println("INFORMAÇÕES CARTÃO DE CRÉDITO: \n");
		System.out.println("Nome: " + cartao.getNome());
		System.out.println("ID: " + cartao.getId());
		System.out.println("Numéro do cartão: " + cartao.getNumCartao());
		System.out.println("Senha do cartão: " + cartao.getSenha());
		System.out.println("Tipo de cartão: " + cartao.getTipoDeCartao());
		System.out.println("Tipo de conta do titular: " + conta.getTipoDeConta());
		System.out.println("Tipo de cliente: " + cliente.getTipoDeCliente());
		System.out.println("Limite diário do Cartão: R$ " + getLimiteDiario());
	}


}
