package br.com.cdb.bancodigital.entity;
import java.time.LocalDate;


public abstract class Cliente {
	//Varíaveis de Instância
	
	protected String cpf;
	protected String nome;
	protected LocalDate dataNascimento;
	private String endereco;
	protected String tipoDeCliente;
	
	

	public Cliente(String nome, String cpf, LocalDate dataDeNascimento) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataDeNascimento;
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