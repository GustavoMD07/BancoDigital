package br.com.cdb.bancodigital.entity;
import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
	//Varíaveis de Instância
	
	protected String cpf;
	protected String nome;
	private long dataDeNascimento;
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

	public Cliente(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
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
	
	public void info() { //faz a mesma coisa que o toString, é só pra deixar mais informativo e ter um info
		System.out.println("Nome: " + nome + "\nCPF: " + cpf + "\n Tipo de cliente: " + tipoDeCliente);
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

	public long getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(long dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}