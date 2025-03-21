package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.entity.Conta;
import br.com.cdb.bancodigital.entity.ContaCorrente;
import br.com.cdb.bancodigital.entity.ContaPoupanca;
import br.com.cdb.bancodigital.DAO.ClienteDAO;
import br.com.cdb.bancodigital.DAO.ContaDAO;
import java.util.Scanner;

public class ContaService {

	private ContaDAO contaDAO;
	private ClienteDAO clienteDAO;
	private ClienteService clienteService;
	private Cliente cliente;
	Scanner input;

	public ContaService(ContaDAO contaDAO, ClienteDAO clienteDAO) {
		this.contaDAO = contaDAO;
		this.clienteDAO = clienteDAO;
		this.clienteService = new ClienteService(clienteDAO);
		input = new Scanner(System.in);
	}

	public Conta buscarContaPorId() {
		int id = input.nextInt();

//		for (Cliente cliente : clienteDAO.getListaDeClientes()) {
			for (Conta conta : contaDAO.getTodasAsContas()) {
				if (conta.getID() == id) {
//					System.out.println("------Conta----------\n" + conta + "\n---------------------");
					return conta;
				}
			}
		
		System.out.println("Erro. Conta não encontrada para o ID: " + id);
		return null;
	}

	public void adicionarConta() {

		Cliente cliente = clienteService.buscarClientePorCpf();
		if (cliente == null) {
			System.out.println("Cliente não encontrado");
			return;
		}

		System.out.println("Escolha o tipo de conta:");
		System.out.println("1 - Conta Corrente");
		System.out.println("2 - Conta Poupança");
		int opcao = input.nextInt();
		input.nextLine(); // Consumir quebra de linha

		Conta novaConta;

		if (opcao == 1) {
			novaConta = new ContaCorrente(cliente);
		} else if (opcao == 2) {
			novaConta = new ContaPoupanca(cliente);
		} else {
			System.out.println("Opção inválida. Conta não criada.");
			return;
		}

		contaDAO.adicionarConta(cliente, novaConta);
		System.out.println("Atenção!. Guarde seu ID pois ele rege todas as funcionalidades");
		System.out.println("ID: " + novaConta.getID());
	}

	public boolean transferencia() {

		System.out.println("Digite o valor da transferência");
		double valor = input.nextDouble();

		System.out.print("Digite o ID da conta de origem: ");
		int idOrigem = input.nextInt();
		input.nextLine(); // Consumir a quebra de linha

		Conta origem = contaDAO.buscarContaPorId(idOrigem);
		if (origem == null) {
			System.out.println("Conta de origem não encontrada.");
			return false;
		}

		System.out.print("Digite o ID da conta de destino: ");
//	    int idDestino = input.nextInt();
//	    input.nextLine(); 

		Conta destino = buscarContaPorId();

		if (destino == null) {
			System.out.println("Erro: Conta de destino inválida.");
			return false;
		}

		if (origem.equals(destino)) {
			System.out.println("Não é possível transferir para a própria conta.");
			return false;
		}

		return origem.transferencia(valor, destino);
	}

	public void depositar() {
		System.out.println("Digite o valor do depósito");
		double valor = input.nextDouble();

		System.out.println("Digite o ID da conta que deseja depositar");
		Conta conta = buscarContaPorId();

		if (conta == null) {
			System.out.println("Erro. Conta não encontrada / inválida");
			return;
		}
		conta.depositar(valor);
	}

	public void sacar() {

		System.out.println("Digite o valor do saque");
		double valor = input.nextDouble();

		System.out.println("Digite o ID da conta que deseja sacar");
		Conta conta = buscarContaPorId();

		if (conta == null) {
			System.out.println("Erro. Conta não encontrada / inválida");
			return;
		}
		conta.saque(valor);
	}

	public void infoConta() {

		System.out.println("Digite o ID da conta que deseja buscar");
		Conta conta = buscarContaPorId();

		if (conta == null) {
			System.out.println("Conta não encontrada");
			return;
		}
		
		Cliente cliente = conta.getCliente(); //to fazendo com que cliente receba o valor de cliente

		System.out.println("\n--------- INFORMAÇÕES GERAIS CONTA ------------\n");
		System.out.println("Titular: " + conta.getTitular());
		System.out.println("Saldo: R$" + conta.getSaldo());
		System.out.println("Tipo de conta: " + conta.getTipoDeConta());
		System.out.println("Tipo de cliente: " + cliente.getTipoDeCliente());
		System.out.println("CPF do cliente associado: " + cliente.getCpf());
		System.out.println("ID da conta: " + conta.getID() + "\n--------------");
	}
}
