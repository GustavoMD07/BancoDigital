package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.entity.Conta;
import br.com.cdb.bancodigital.entity.ContaCorrente;
import br.com.cdb.bancodigital.entity.ContaPoupanca;
import br.com.cdb.bancodigital.DAO.ClienteDAO;
import br.com.cdb.bancodigital.DAO.ContaDAO;
import java.util.List;
import java.util.Scanner;

public class ContaService {

	private ContaDAO contaDAO;
	private ClienteDAO clienteDAO = new ClienteDAO();
	Scanner input;

	public ContaService(ContaDAO contaDAO) {
		this.contaDAO = contaDAO;
		input = new Scanner(System.in);
	}
	
	public Conta buscarContaPorId(int idConta) {
		System.out.println("Digite o id da conta que deseja buscar");
		int id = input.nextInt();
		
		Conta conta = contaDAO.buscarContaPorId(idConta);
		if (conta == null) {
			System.out.println("Erro. Conta não encontrada para o ID: " + idConta);
		}
		return conta;
	}
	
	public void adicionarConta() {
        System.out.println("Digite o CPF do cliente para criar a conta:");
        String cpf = input.nextLine();
        
        Cliente cliente = clienteDAO.buscarCliente(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado.");
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
        System.out.println("Conta criada com sucesso! Titular: " + novaConta.getTitular());
        System.out.println("Atenção, guarde seu ID pois ele rege todas as funcionalidades");
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
	    int idDestino = input.nextInt();
	    input.nextLine(); 

	    Conta destino = buscarContaPorId(idDestino);
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
		
		System.out.println("Digite o id da conta que deseja depositar");
		int id = input.nextInt();
		
		Conta conta = buscarContaPorId(id);
		
		if (conta == null) {
			System.out.println("Erro. Conta não encontrada / inválida");
			return;
		}
		conta.depositar(valor);
	}

	public void sacar() {
		System.out.println("Digite o valor da transferência");
		double valor = input.nextDouble();
		
		System.out.println("Digite o id da conta que deseja sacar");
		int id = input.nextInt();
		
		Conta conta = buscarContaPorId(id);
		
		if (conta == null) {
			System.out.println("Erro. Conta não encontrada / inválida");
			return;
		}
		conta.saque(valor);
	}
	
	public void infoConta() {
		System.out.println("Digite o ID da conta para acessar as informações");
		int id = input.nextInt();
		Conta conta = buscarContaPorId(id);
		
		if(conta == null) {
			System.out.println("Conta não encontrada");
			return;
		}
		
		System.out.println("\n INFORMAÇÕES CONTA");
		System.out.println("Titular: " + conta.getTitular());
		System.out.println("Saldo: " + conta.getSaldo());
		System.out.println("Tipo de conta: " + conta.getTipoDeConta());
		System.out.println("ID: " + conta.getID());
	}
}
