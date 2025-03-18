package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.DAO.ClienteDAO;
import br.com.cdb.bancodigital.entity.Cliente;
import java.util.List;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ClienteService {

	private ClienteDAO clienteDAO;
	
	private static final Pattern nomePadrao = Pattern.compile("^[//p{L} ]+$");
	//aqui eu to definindo o padrão, pode ser letra maíscula, minúscula, acento, etc
	
	private static final DateTimeFormatter dataFormato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	//padronizando a data pra ser do formato DD/MM/AAAA
	
	
	public ClienteService(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public void adicionarCliente(Cliente cliente) {
		
		LocalDate idadeMinima = LocalDate.now().minusYears(18); 
		//subtrai 18 anos da data atual, e guarda que o mínimo que o usuário precisa colocar é 18/03/2007
		
		if (cliente == null) {
			System.out.println("Cliente não encontrado / inválido");
			return;
		}
		
		String nome = cliente.getNome().trim(); //remove os espaços desnecessários
		
		if(cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
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
		
		if(cliente.getDataNascimento() == null) {
			System.out.println("A data de nascimento não pode ser nula");
		}
		
		if(cliente.getDataNascimento().isAfter(idadeMinima)) {
			System.out.println("É necessário ter 18 anos para ser cliente");
			return;
		}

		if (clienteDAO.buscarCliente(cliente.getCpf()) != null) {
			System.out.println("Erro!. Já há um cliente cadastrado com esse cpf.");
			return;
		}
		
		if(!verificarFormatoCpf(cliente.getCpf())) { //verificando se o cpf tá no formato
			System.out.println("CPF com formato inválido");
			return;
		}
		
		if(!verificarCpf(cliente.getCpf())) { //verificando se tem 11 digitos e os dois últimos
			System.out.println("Cpf inválido");
			return;
		}
		
		clienteDAO.addCliente(cliente);
		System.out.println("Cliente: " + cliente.getNome() + " adicionado com sucesso");
	}

	public Cliente buscarClientePorNome(String nome) {
		List<Cliente> clientes = clienteDAO.getListaDeClientes();

		for (Cliente cliente : clientes) {
			if (cliente.getNome().equalsIgnoreCase(nome)) {
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
		String regex = "^[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}\\$";
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
	
	
	

}
