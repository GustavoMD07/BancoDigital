package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.DAO.ClienteDAO;
import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.entity.ClienteComum;
import br.com.cdb.bancodigital.entity.ClientePremium;
import br.com.cdb.bancodigital.entity.ClienteSuper;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ClienteService {

	private ClienteDAO clienteDAO;
	Scanner input;
	
	private static final DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	//padronizando a data pra ser do formato DD/MM/AAAA
	
	
	public ClienteService(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
		input = new Scanner(System.in);
	}

	public void adicionarCliente() {
		
		System.out.println("Digite seu nome: ");
		String nome = input.nextLine().trim();//remove os espaços desnecessários e pega o nome
		
		if(nome == null || nome.trim().isEmpty()) {
			System.out.println("O nome não pode ser nulo");
			return;
			//verificando se só tem espaços
		}
		
		if(nome.length() < 2 || nome.length() > 100) {
			System.out.println("O nome deve ter mais de 2 letras e menos de 100");
			return;
		}
		
		System.out.println("Digite sua data de nascimento (dd/mm/aaaa)");
		String dataDigitada = input.nextLine();
		
		if(dataDigitada == null) {
			System.out.println("A data de nascimento não pode ser nula");
			return;
		}
		
		try {
			LocalDate dataNascimento = LocalDate.parse(dataDigitada, dataFormato);
			
			LocalDate idadeMinima = LocalDate.now().minusYears(18); 
			//subtrai 18 anos da data atual, e guarda que o mínimo que o usuário precisa colocar é 18/03/2007
		
			if(dataNascimento.isAfter(idadeMinima)) {
				System.out.println("É necessário ter 18 anos para ser cliente");
				return;
			} else {
				System.out.println("Data correta");
			}
		}
		catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido! Use o formato dd/MM/yyyy.");
        } 
		
		LocalDate dataNascimento = LocalDate.parse(dataDigitada, dataFormato);
		

		System.out.println("Digite seu cpf: ");
		String cpf = input.next().trim();
		
		if(!verificarFormatoCpf(cpf)) { //verificando se o cpf tá no formato
			System.out.println("CPF com formato inválido");
			return;
		}
		
		if(!verificarCpf(cpf)) { //verificando se tem 11 digitos e os dois últimos
			System.out.println("Cpf inválido");
			return;
		}
		
		if (clienteDAO.buscarCliente(cpf) != null) {
			System.out.println("Erro!. Já há um cliente cadastrado com esse cpf.");
			return;
		}
		
		Cliente cliente = selecionarTipoDeCliente(nome, cpf, dataNascimento);
		
		clienteDAO.addCliente(cliente);
		System.out.println("\nCLIENTE\n-------------\n" + cliente + " \nCliente adicionado com sucesso\n");
	}

	public Cliente buscarClientePorCpf() {
		
		System.out.println("Digite o CPF do cliente que deseja buscar: ");
	    String cpf = input.nextLine().trim();
	    System.out.println("\n");
	    
	    
	    if (!verificarFormatoCpf(cpf)) {
	        System.out.println("CPF com formato inválido.");
	        return null;
	    }

	    if (!verificarCpf(cpf)) {
	        System.out.println("CPF inválido.");
	        return null;
	    }

		
		List<Cliente> clientes = clienteDAO.getListaDeClientes(); //a lista não tá vazia
		//System.out.println("Clientes cadastrados: " + clientes); //lembrar de ver o erro da Conta

		for (Cliente cliente : clientes) {
			//System.out.println("Buscando CPF: " + cpf);  // CPF que você está buscando
		    //System.out.println("CPF do cliente: " + cliente.getCpf());  // CPF do cliente na lista
			if (cliente.getCpf().equals(cpf)) {
				System.out.println("------Cliente----------\n" + cliente + "\n---------------------");
				return cliente;
			}
		}
		System.out.println("Cliente não encontrado para a busca do CPF");
		return null;
	}

	public void listarClientes() {
		List<Cliente> clientes = clienteDAO.getListaDeClientes();

		if (clientes.isEmpty()) {
			System.out.println("Não vejo nenhum cliente...");
			return;
		}
		for (Cliente cliente : clientes) {
			System.out.println("\n");
			System.out.println(cliente);
			
		}
	}
	
	public String formatarFormatoData(LocalDate dataNascimento) {
		return dataNascimento.format(dataFormato);
	}

	public boolean verificarFormatoCpf(String cpf) {
		String regex = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$";
		return cpf.matches(regex);
		// aqui faz a verificação se o formato do cpf está correto, usando o regex pra definir um padrão :)
		// depois ele retorna pra verificar se o cpf tá batendo com o que foi feito no regex
	}

	public boolean verificarCpf(String cpf) {
		cpf = cpf.replaceAll("[^0-9]",  "");
		// só pra ter certeza que qualquer número fora do formato seja removido

		if (cpf.length() != 11) {
			return false;
		}
		return true;
	}
	
	private Cliente selecionarTipoDeCliente(String nome, String cpf, LocalDate dataNascimento) {
		System.out.println("Selecione o tipo de cliente: ");
		System.out.println("1 - Comum\n2 - Super\n3 - Premium ");
		int tipo = input.nextInt();
		input.nextLine();
		 switch (tipo) {
	        case 1:
	            return new ClienteComum(nome, cpf, dataNascimento);
	        case 2:
	            return new ClienteSuper(nome, cpf, dataNascimento);
	        case 3:
	            return new ClientePremium(nome, cpf, dataNascimento);
	        default:
	            System.out.println("Opção inválida. Criando cliente comum por padrão.");
	            return new ClienteComum(nome, cpf, dataNascimento);
	    }
	}
	
	public void infoCliente() {
		
	    Cliente cliente = buscarClientePorCpf();
	    
	    if (cliente == null) {
	        System.out.println("Cliente não encontrado.");
	        return;
	    }

	    System.out.println("\n--- Informações do Cliente ---");
	    System.out.println("Nome: " + cliente.getNome());
	    System.out.println("CPF: " + cliente.getCpf());
	    System.out.println("Tipo de Cliente: " + cliente.getTipoDeCliente());
	    System.out.println("--------------------------------\n");
	}

}
