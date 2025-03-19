package br.com.cdb.bancodigital.entity;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Cartao {

	private static final AtomicInteger contador = new AtomicInteger(0);  // Contador para gerar IDs únicos
	protected String tipoDeCartao;
	protected boolean status;
	protected String senha;
	protected String numCartao;
	protected Conta conta;
	protected String nome;

	private final Integer id;  

	public Cartao(String senha, String numCartao, Conta conta) {
		this.senha = senha;
		this.numCartao = numCartao;
		this.id = contador.incrementAndGet(); //gerando ID único pra cada cartão
		this.conta = conta;
		this.status = true;
		this.nome = conta.getTitular();
	}

	public boolean ativar() {
		System.out.println("Seu cartão foi disponibilizado para realizar as operações");
		status = true;
		return true;
	}

	public boolean desativar() {
		System.out.println("Seu cartão foi desativado com sucesso");
		status = false;
		return false;
	}

	public void alterarSenha(String novaSenha) {
		this.senha = novaSenha;
		System.out.println("Sua senha do cartão foi alterada para: " + novaSenha);
	}
	
	public void verificarId() {
		System.out.println("ID: " + id );
	}

	public abstract void aplicarTaxas();
	public abstract void realizarPagamento(double valor);

	public void infoGeral(Cartao c) {

		System.out.println("Nome: " + nome + " || Senha: " + senha + "  \nNum: " + numCartao + "|| Status: " + status
		+ "\nTipo de cartão: " + tipoDeCartao);
	}

	@Override
	public String toString() {
		return "nome: " + nome + "senha: " + senha + "num: " + numCartao + "status: " + status + "tipo de cartão: "
				+ tipoDeCartao;
	}

	public void verificarStatus() {
		System.out.println(status ? "Seu cartão está ativado!" : "Seu cartão está desativado :(");
	}   //mais simplificado do que colocar if 2x.

	public String getSenha() {
		return senha;
	}

	public String getNumCartao() {
		return numCartao;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
}
