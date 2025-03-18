package br.com.cdb.bancodigital.entity;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class Seguro {

	private static final AtomicInteger contadorApolice = new AtomicInteger(1000);
	private String numApolice;
	private LocalDate dataContratacao;
	private CartaoCredito cartaoCoberto;
	private double valorApolice;
	private String condicoes;
	
	public Seguro(CartaoCredito cartaoCoberto, double valorApolice, String condicoes) {
		this.numApolice = "APO-" + contadorApolice.incrementAndGet();
		this.dataContratacao = LocalDate.now();
		this.cartaoCoberto = cartaoCoberto;
		this.valorApolice = valorApolice;
		this.condicoes = condicoes;
	}

	public void exibirDetalhes() {
		System.out.println("=====  Apólice de Seguro  =====");
		System.out.println("Número: #" + numApolice);
		System.out.println("Data de contratação: " + dataContratacao);
		System.out.println("Cartão coberto " + cartaoCoberto.getNumCartao());
		System.out.println("Titular: " + cartaoCoberto.getNome());
		System.out.println("Valor da Apólice: R$ " + valorApolice);
		System.out.println("Condições: " + condicoes);
	}
	
	
									//Getters & Setters
	
	public String toString() {
		return "Apólice #" + numApolice +
                "\nData: " + dataContratacao +
                "\nCartão: " + cartaoCoberto +
                "\nValor: R$ " + valorApolice +
                "\nCondições: " + condicoes; 
	}
	
	public static AtomicInteger getContadorapolice() {
		return contadorApolice;
	}

	public String getNumeroApolice() {
		return numApolice;
	}

	public LocalDate getDataContratacao() {
		return dataContratacao;
	}

	public CartaoCredito getCartaoCoberto() {
		return cartaoCoberto;
	}

	public double getValorApolice() {
		return valorApolice;
	}

	public String getCondicoes() {
		return condicoes;
	}
}
