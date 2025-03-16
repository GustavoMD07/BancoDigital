package br.com.cdb.bancodigital.entity;

public abstract class Cartao {

	protected String tipoDeCartao;
	protected boolean status;
	protected String senha;
	protected double numCartao;
	protected Conta conta;
	protected String nome;

	public Cartao(String senha, double numCartao, Conta conta) {
		this.senha = senha;
		this.numCartao = numCartao;
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
		if (status) {
			System.out.println("Seu cartão está ativado");
		} else if (!status) {
			System.out.println("Seu cartão está desativado, ative-o para usa-lo");
		}
	}

	public String getSenha() {
		return senha;
	}

	public double getNumCartao() {
		return numCartao;
	}
}
