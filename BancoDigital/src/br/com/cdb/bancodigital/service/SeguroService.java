package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.DAO.CartaoDAO;
import br.com.cdb.bancodigital.DAO.ClienteDAO;
import br.com.cdb.bancodigital.DAO.SeguroDAO;
import br.com.cdb.bancodigital.entity.*;
import java.util.Scanner;

public class SeguroService {

	private SeguroDAO seguroDAO;
	private CartaoDAO cartaoDAO = new CartaoDAO();
	private CartaoService cartaoService = new CartaoService(cartaoDAO);
	private ClienteDAO clienteDAO = new ClienteDAO();
	private ClienteService clienteService = new ClienteService(clienteDAO);
	Scanner input;

	public SeguroService(SeguroDAO seguroDAO) {
		this.seguroDAO = seguroDAO;
		input = new Scanner(System.in);
	}

	public Seguro seguroViagem() {

		System.out.println("Digite o ID do cartão que deseja aplicar o seguro viagem");
		int id = input.nextInt();
		Cartao cartao = cartaoService.buscarCartao();

		if (cartao instanceof CartaoDebito) {
			System.out.println("Cartão de débito não pode receber seguro");
			return null;
		}

		if (cartao == null) {
			System.out.println("Cartão não identificado");
			return null;
		}

		System.out.println("Digite o cpf do cliente que deseja aplicar o seguro");
		String cpf = input.next().trim();
		Cliente cliente = clienteService.buscarClientePorCpf();

		if (!clienteService.verificarFormatoCpf(cpf)) {
			System.out.println("CPF com formato inválido.");
			return null;
		}

		if (!clienteService.verificarCpf(cpf)) {
			System.out.println("CPF inválido.");
			return null;
		}

		double valorApolice = 0.0;
		String condicoes = ""; // bug de inicialização

		if (cartao == null || cliente == null) {
			System.out.println("Cliente ou cartão não encontrado.");
			return null;
		}

		if (cliente instanceof ClienteComum || cliente instanceof ClienteSuper) {
			valorApolice = 50.0;
			condicoes = "Cobertura para viagens internacionais acionada. Taxa mensal: R$ " + valorApolice;
		}

		if (cliente instanceof ClientePremium) {
			valorApolice = 0.0;
			condicoes = "Cobertura para viagens internacionais acionada. Gratuito para clientes Premium :)";
		}

		Seguro seguro = new Seguro(cartao, valorApolice, condicoes);
		cartao.addSeguro(seguro);
		seguroDAO.addSeguro(seguro);
		return seguro;
	}

	public Seguro seguroFraude() {

		System.out.println("Digite o ID do cartão que deseja aplicar o seguro fraude");
		int id = input.nextInt();
		Cartao cartao = cartaoService.buscarCartao();

		if (cartao instanceof CartaoDebito) {
			System.out.println("Cartão de débito não pode receber seguro");
			return null;
		}

		if (cartao == null) {
			System.out.println("Cartão não identificado");
			return null;
		}

		System.out.println("Digite o cpf do cliente que deseja aplicar o seguro");
		String cpf = input.next().trim();
		Cliente cliente = clienteService.buscarClientePorCpf();

		if (!clienteService.verificarFormatoCpf(cpf)) {
			System.out.println("CPF com formato inválido.");
			return null;
		}

		if (!clienteService.verificarCpf(cpf)) {
			System.out.println("CPF inválido.");
			return null;
		}

		if (cartao == null || cliente == null) {
			System.out.println("Cliente ou cartão não encontrado.");
			return null;
		}
		double valorApolice = 5000.0;
		String condicoes = "Cobertura contra fraudes acionada. Valor de até R$ " + valorApolice;

		Seguro seguro = new Seguro(cartao, valorApolice, condicoes);
		cartao.addSeguro(seguro);
		seguroDAO.addSeguro(seguro);
		return seguro;
	}
	
	public Seguro buscarSeguroApolice() {
		System.out.println("Digite o número da apólice para o seguro que deseja buscar");
		String numApolice = input.nextLine();
		
		Seguro seguro = seguroDAO.buscarSeguroPorNumeroApolice(numApolice);
		if(seguro == null) {
			System.out.println("Seguro não encontrado");
			return null;
		}
		return seguro;
	}
	
	public void infoApolice() {
		System.out.println("Digite o número da ápolice para verificar as informações ");
		String numApolice = input.nextLine();
		Seguro seguro = buscarSeguroApolice();
		
		if(seguro == null) {
			System.out.println("Seguro não encontrado");
			return;
		}
		
		System.out.println("INFORMAÇÕES SEGURO: ");
		System.out.println("Numéro da ápolice: " + seguro.getNumeroApolice());
		System.out.println("Condições: " + seguro.getCondicoes());
		System.out.println("Data: " + seguro.getDataContratacao());
		System.out.println("Valor: " + seguro.getValorApolice());
		System.out.println("Cartão coberto: " + seguro.getCartaoCoberto());
	}

	public void listarSeguros() {
		for (Seguro seguro : seguroDAO.listarSeguros()) {
			System.out.println(seguro);
		}
	}

}
