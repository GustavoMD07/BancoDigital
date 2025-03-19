package br.com.cdb.bancodigital.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Conta {

	private static final AtomicInteger contador = new AtomicInteger(0); // Contador para gerar IDs únicos
	private String titular; // (nome)
	protected Integer ID;
	protected Cliente cliente;
	protected double saldo;
	protected boolean transf;
	protected String tipoDeConta;

	
	public Conta(Cliente cliente) {
		this.cliente = cliente;
		this.titular = cliente.getNome();
		this.saldo = 0; // saldo 0 toda vez que for criar uma nova conta
		this.ID = contador.incrementAndGet(); // gerando ID único pra cada conta
		agendarTaxaMensal();
	}

	public abstract void depositar(double valor);

	public abstract boolean saque(double valor);

	public abstract boolean transferencia(double valor, Conta conta);

	public abstract void taxa();

	public abstract void saldo();

	protected void agendarTaxaMensal() {
		try {
			ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
			long delay = calcularDelay(); // aqui ele calcula o delay (tempo) até o próximo mês
			long intervalo = TimeUnit.DAYS.toMillis(30); // convertendo 30 dias pra milliseconds
			scheduler.scheduleAtFixedRate(this::taxa, delay, intervalo, TimeUnit.MILLISECONDS);
		} catch (Exception e) {
			System.out.println("Erro ao agendar taxa: " + e.getMessage());
		}
	}

	// Getters and Setters, não precisa mexer daqui pra baixo

	protected void addSaldo(double valor) {
		this.saldo += valor;
	}

	protected void retirarSaldo(double valor) {
		this.saldo -= valor;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer ID) {
		this.ID = ID;
	}

	public double getSaldo() {
		return saldo;
	}
	
	public String getTipoDeConta() {
		return tipoDeConta;
	}


	// o toString é muito útil sim.
	public String toString() {
		return "\nNome do titular: " + titular + "\nSaldo: R$ " + saldo + "\nTipo de conta: " + tipoDeConta;
	}

	public void info() {
		System.out
				.println("\nNome do titular: " + titular + "\nSaldo: R$ " + saldo + "\nTipo de conta: " + tipoDeConta);
	}

	private long calcularDelay() {
		// Vamos calcular o delay até o próximo dia 1º do mês
		LocalDate hoje = LocalDate.now();
		LocalDate primeiroDiaProximoMes = hoje.withDayOfMonth(1).plusMonths(1);

		// Calcular o número de milissegundos até o próximo dia 1º
		long delay = ChronoUnit.MILLIS.between(hoje.atStartOfDay(), primeiroDiaProximoMes.atStartOfDay());
		return delay;
	}
}