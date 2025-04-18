package br.com.cdb.bancodigital.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Cartao {

	private static final AtomicInteger contador = new AtomicInteger(0); // Contador para gerar IDs únicos
	protected String tipoDeCartao;
	protected boolean status;
	

	protected String senha;
	protected String numCartao;
	protected Conta conta;
	protected Cliente cliente;
	protected String nome;
	protected List<Seguro> seguros = new ArrayList<>();

	private final int id;

	public Cartao(String senha, String numCartao, Conta conta, Cliente cliente) {
		this.senha = senha;
		this.numCartao = numCartao;
		this.id = contador.incrementAndGet(); // gerando ID único pra cada cartão
		this.conta = conta;
		this.cliente = cliente;
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
		System.out.println("ID: " + id);
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
	} // mais simplificado do que colocar if 2x.

	public String getSenha() {
		return senha;
	}

	public String getNumCartao() {
		return numCartao;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Cliente getCliente() {  //aqui eu preciso pegar o cliente e a conta pra no futuro retornar
		return this.cliente;       //alguma informação deles
	}							   //meio que eu to dando uma chave pra que outras partes do meu código
								   //possam olhar dentro dessas classes
	public Conta getConta() {
		return this.conta;
	}

	public String getTipoDeCartao() {
		return tipoDeCartao;
	}

	public List<Seguro> getSeguros() {
		return seguros;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public String getStatusString() {
	    return status ? "Ativado" : "Desativado";
	} //se o status for true, ele retorna ativado, se for false, ele retorna desativado
	//assim dá pra mostrar no terminal

	public void addSeguro(Seguro seguro) {

		if (seguro != null && !seguros.contains(seguro)) {
			seguros.add(seguro);
		}
	}
}
