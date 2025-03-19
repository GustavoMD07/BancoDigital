package br.com.cdb.bancodigital.entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
	//Varíaveis de Instância
	
	protected String cpf;
	protected String nome;
	protected LocalDate dataNascimento;
	private String endereco;
	protected String tipoDeCliente;
	protected List<ContaPoupanca> contaPoupanca;
	protected List<ContaCorrente> contaCorrente;
	protected List<Conta> todasAsContas;
	
	public List<ContaPoupanca> getContaPoupanca() {
		return contaPoupanca;
	}

	public List<ContaCorrente> getContaCorrente() {
		return contaCorrente;
	}

	public Cliente(String nome, String cpf, LocalDate dataDeNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataDeNascimento;
		this.contaCorrente = new ArrayList<>();
		this.contaPoupanca = new ArrayList<>();
		this.todasAsContas = new ArrayList<>();
	}
	
	public List<Conta> getTodasAsContas() {
		List<Conta> todas = new ArrayList<>();
		todas.addAll(contaCorrente);
		todas.addAll(contaPoupanca);
		return todas;
	}
	
	public void adicionarConta(Conta conta) {            //usando o instaceof pra identificar se é uma conta corrente ou 
		if (conta instanceof ContaCorrente) {			 // uma conta poupança, ele vai adicionar no lugar certo
			this.contaCorrente.add((ContaCorrente) conta);
			System.out.println("Conta corrente adicionada : " + conta + "\n");
		}
		else if (conta instanceof ContaPoupanca) {
			this.contaPoupanca.add((ContaPoupanca) conta);
			System.out.println("Conta poupança adicionada : " + conta + "\n");
		}
	}
	
	public void removerConta(Conta conta) {
		if (conta instanceof ContaCorrente) {			 // uma conta poupança, ele vai adicionar no lugar certo
			this.contaCorrente.remove((ContaCorrente) conta);
			System.out.println("Conta corrente removida : " + conta + "\n");
		}
		else if (conta instanceof ContaPoupanca) {
			this.contaPoupanca.remove((ContaPoupanca) conta);
			System.out.println("Conta poupança removida : " + conta + "\n");
		}
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + "\nCPF: " + cpf + "\nTipo de cliente: " + tipoDeCliente;
	}

	
	
	
	//getters and setters, não precisa mexer daqui pra baixo
	
	public String getTipoDeCliente() {
		return tipoDeCliente;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}