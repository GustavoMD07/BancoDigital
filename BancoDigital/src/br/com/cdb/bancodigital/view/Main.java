package br.com.cdb.bancodigital.view; //fazendo o view pra ser o visual do código, pra poder testar

//import java.util.Scanner;

import java.util.concurrent.*;
import br.com.cdb.bancodigital.DAO.*; //importando os outros pacotes pra ficar no formato MVC
import br.com.cdb.bancodigital.entity.*;



public class Main {
	public static void main(String[] args)  {
		
		ClienteDAO clientes = new ClienteDAO();
		
		Cliente cliente = new ClienteComum("Gustavo", "1");
		Cliente clienteP = new ClientePremium("Henrique", "2");

		
		ContaCorrente conta = new ContaCorrente("Gustavo", 10, TimeUnit.DAYS);
		
     	CartaoDebito cartao = new CartaoDebito("asd", "1000", 400, conta);
     	Cartao cartaoC = new CartaoCredito("asd", "1000", 400, conta);
		ContaPoupanca contaP = new ContaPoupanca("Henrique", 10, TimeUnit.DAYS);
		
		
		cartao.verificarId();
		cartaoC.verificarId();		
		
		
		
		
//		ContaDAO contas = new ContaDAO(clientes);
//		clientes.addCliente(cliente);
//		clientes.addConta(cliente, conta);
//		contas.listarContas();		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
