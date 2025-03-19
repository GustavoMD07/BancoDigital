package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.DAO.ClienteDAO;
import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.entity.ClienteComum;
import br.com.cdb.bancodigital.entity.ClientePremium;
import br.com.cdb.bancodigital.entity.ClienteSuper;

import java.util.List;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ClienteService {

	private ClienteDAO clienteDAO;
	Scanner input;
	
	private static final Pattern nomePadrao = Pattern.compile("^[//p{L} ]+$");
	//aqui eu to definindo o padrão, pode ser letra maíscula, minúscula, acento, etc
	
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
		
		if(!nomePadrao.matcher(nome).matches()) {
			System.out.println("O nome deve conter apenas letras e espaços");
			//aqui ele verifica com o matcher se tá no formato regex e matches pra retornar se tá tudo certo
		}
		
		System.out.println("Digite sua data de nascimento (dd/mm/aaaa)");
		LocalDate dataNascimento = LocalDate.parse(input.nextLine());
		
		LocalDate idadeMinima = LocalDate.now().minusYears(18); 
		//subtrai 18 anos da data atual, e guarda que o mínimo que o usuário precisa colocar é 18/03/2007
		
		if(dataNascimento == null) {
			System.out.println("A data de nascimento não pode ser nula");
		}
		
		if(dataNascimento.isAfter(idadeMinima)) {
			System.out.println("É necessário ter 18 anos para ser cliente");
			return;
		}

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
		System.out.println("Cliente: " + cliente + " adicionado com sucesso");
	}

	public Cliente buscarClientePorCpf() {
		System.out.println("Digite o CPF do cliente que deseja buscar: ");
	    String cpf = input.next().trim();
	    
	    if (!verificarFormatoCpf(cpf)) {
	        System.out.println("CPF com formato inválido.");
	        return null;
	    }

	    if (!verificarCpf(cpf)) {
	        System.out.println("CPF inválido.");
	        return null;
	    }

		
		List<Cliente> clientes = clienteDAO.getListaDeClientes();

		for (Cliente cliente : clientes) {
			if (cliente.getCpf().equals(cpf)) {
				return cliente;
			}
		}
		System.out.println("Cliente não encontrado");
		return null;
	}

	public void listarClientes() {
		List<Cliente> clientes = clienteDAO.getListaDeClientes();

		if (clientes.isEmpty()) {
			System.out.println("Não vejo nenhum cliente...");
			return;
		}
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}
	}
	
	public String formatarFormatoData(LocalDate dataNascimento) {
		return dataNascimento.format(dataFormato);
	}

	private boolean verificarFormatoCpf(String cpf) {
		String regex = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}$";
		return cpf.matches(regex);
		// aqui faz a verificação se o formato do cpf está correto, usando o regex pra definir um padrão :)
		// depois ele retorna pra verificar se o cpf tá batendo com o que foi feito no regex
	}

	private boolean verificarCpf(String cpf) {
		cpf = cpf.replaceAll("[^0-9", "");
		// só pra ter certeza que qualquer número fora do formato seja removido

		if (cpf.length() != 11) {
			return false;
		}

		//verificação dos dois últimos digitos (digitos verificadores)
		int soma = 0;
		for (int i = 0; i < 9; i++) {
			soma += (10 - i) * (cpf.charAt(i) - '0'); // por algum motivo ele tá dando erro com ""
		}
		int primeiroDigito = (soma % 11 < 2) ? 0 : 11 - (soma % 11);
		if (cpf.charAt(9) - '0' != primeiroDigito) {
			return false;
		}

		soma = 0;
		for (int i = 0; i < 10; i++) {
			soma += (11 - i) * (cpf.charAt(i) - '0');
		}
		int segundoDigito = (soma % 11 < 2) ? 0 : 11 - (soma % 11);
		return cpf.charAt(10) - '0' == segundoDigito;
	}
	
	private Cliente selecionarTipoDeCliente(String nome, String cpf, LocalDate dataNascimento) {
		System.out.println("Selecione o tipo de cliente: ");
		System.out.println("1 - Comum\n 2 - Super\n 3 - Premium ");
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
	    System.out.println("Digite o CPF do cliente para exibir informações: ");
	    String cpf = input.next().trim();

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
