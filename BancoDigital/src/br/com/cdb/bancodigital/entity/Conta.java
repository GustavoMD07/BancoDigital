package br.com.cdb.bancodigital.entity;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class Conta {

	private static final AtomicInteger contador = new AtomicInteger(0);  // Contador para gerar IDs únicos
	private String titular; //(nome)
	protected Integer ID;
	protected double saldo;
	protected boolean transf;
	protected String tipoDeConta;
	
	public Conta(String titular, int intervalo, TimeUnit tempo) {
		this.titular = titular;
		this.saldo = 0; //saldo 0 toda vez que for criar uma nova conta
		this.ID = contador.incrementAndGet(); //gerando ID único pra cada conta
		agendarTaxaMensal(intervalo, tempo);
	}
	
	public abstract void depositar(double valor);
	public abstract boolean saque(double valor);
	public abstract boolean transferencia(double valor, Conta conta) ;
	public abstract void taxa();
	public abstract void saldo();
	
	protected void agendarTaxaMensal(int intervalo, TimeUnit tempo) {
		try {
	        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	        scheduler.scheduleAtFixedRate(this::taxa, intervalo, intervalo, tempo);
	    } catch (Exception e) {
	        System.out.println("Erro ao agendar taxa: " + e.getMessage());
	        e.printStackTrace(); // Imprime a stack trace para debug
	    }
	}
	
	//Getters and Setters, não precisa mexer daqui pra baixo
	
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
		return saldo ;
	}
								//o toString é muito útil sim.
	public String toString() {
		return "\nNome do titular: " + titular + "\nSaldo: R$ " + saldo + "\nTipo de conta: " + tipoDeConta;
	}
	
	public void info() {
		System.out.println("\nNome do titular: " + titular + "\nSaldo: R$ " + saldo + "\nTipo de conta: " + tipoDeConta);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}