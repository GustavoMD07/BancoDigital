package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.DAO.CartaoDAO;
import br.com.cdb.bancodigital.DAO.ClienteDAO;
import br.com.cdb.bancodigital.DAO.ContaDAO;
import br.com.cdb.bancodigital.entity.Cartao;
import br.com.cdb.bancodigital.entity.CartaoDebito;
import br.com.cdb.bancodigital.entity.CartaoCredito;
import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.entity.Conta;

import java.util.List;
import java.util.Scanner;

public class CartaoService {

	CartaoDAO cartaoDAO;
	ClienteDAO clienteDAO;
	CartaoCredito cartaoCredito;
	CartaoDebito cartaoDebito;
	ClienteService clienteService;
	ContaDAO contaDAO;
	Conta conta;
	Cliente cliente;
	ContaService contaService;
	Scanner input;

	public CartaoService(CartaoDAO cartaoDAO, ContaService contaService, ClienteDAO clienteDAO) {
		input = new Scanner(System.in);
		this.cartaoDAO = cartaoDAO;
		this.contaService = contaService;
		this.clienteDAO = clienteDAO;
		this.clienteService = new ClienteService(clienteDAO);
	}

	private boolean verificarSenha(Cartao cartao, String senha) {
		return cartao.getSenha().equals(senha); // método de verificação de senha para poder prosseguir
	}

	public void addCartao() {

		System.out.println("A senha deve conter pelo menos um caractere maísculo e um número");
		String senha;
		do {
			System.out.println("Digite a senha do cartão: ");
			senha = input.nextLine().trim();

			if (senha == null || senha.trim().isEmpty()) {
				System.out.println("A senha não pode estar vazia");
				return;
			}

			if (senha.length() < 5) {
				System.out.println("A senha precisa ter mais de cinco digitos!");
			}

			if (!validarSenha(senha)) {
				System.out.println("Atenção: a senha deve conter pelo menos um caractere maiúsculo e um número.");
			}

		} while (!validarSenha(senha));

		System.out.println("Digite o número do cartão ");
		String num = input.nextLine().trim();

		if (num == null || num.trim().isEmpty()) {
			System.out.println("O número do cartão não pode estar vazio");
			return;
		}

		System.out.println("Digite o ID da conta para associar ao cartão: ");

		Conta conta = contaService.buscarContaPorId();
		if (conta == null) {
			System.out.println("Não existe a conta para o ID referenciado");
			return;
		}

		Cliente cliente = clienteService.buscarClientePorCpf();

		if (cliente == null) {
			System.out.println("Cliente não encontrado.");
			return;
		}

		System.out.println("Escolha o tipo de cartão:");
		System.out.println("1 - Cartão de Crédito");
		System.out.println("2 - Cartão de Débito");
		int opcao = input.nextInt();
		input.nextLine(); // quebra de linha pra não bugar ou pelo menos tentar

		Cartao novoCartao;

		if (opcao == 1) {
			novoCartao = new CartaoCredito(senha, num, conta, cliente);
		}

		else if (opcao == 2) {
			System.out.println("Digite o limite diário para o cartão de débito");
			double limiteDiario = input.nextDouble();
			novoCartao = new CartaoDebito(senha, num, limiteDiario, conta, cliente);
		} else {
			System.out.println("Opção inválida. Cartão não criado.");
			return;
		}

		cartaoDAO.addCartao(novoCartao);
		System.out.println("Cartão criado para o titular da conta: " + conta.getTitular());
		System.out.println("Senha do cartão: " + novoCartao.getSenha());
		System.out.println("ID do cartão: " + novoCartao.getId());
	}

	public void ativarCartao() {

		System.out.println("Digite o ID do cartão que deseja ativar");

		Cartao cartao = buscarCartao();
		if (cartao == null) {
			System.out.println("Cartão não encontrado!.");
			return;
		}
		
		input.nextLine();
		
		if(cartao.isStatus()) {
			System.out.println("Seu cartão já está ativado ");
			return;
		}

		System.out.println("Digite a senha do cartão que deseja ativar");
		String senha = input.nextLine();

		if (verificarSenha(cartao, senha)) {
			cartao.ativar();
		} else {
			System.out.println("Senha incorreta, não foi possível ativar o cartão");
		}
	}

	public void desativarCartao() {

		System.out.println("Digite o ID do cartão que deseja desativar");

		Cartao cartao = buscarCartao();
		if (cartao == null) {
			System.out.println("Cartão não encontrado");
			return;
		}
		input.nextLine();
		
		if(cartao.isStatus() == false) {
			System.out.println("Seu cartão já está desativado");
			return;
		}

		System.out.println("Digite a senha do cartão que deseja desativar");
		String senha = input.nextLine();

		if (verificarSenha(cartao, senha)) {
			cartao.desativar();
		} else {
			System.out.println("Senha incorreta, não foi possível desativar o cartão");
		}
	}

	public void alterarSenha() {

		System.out.println("Digite o id do cartão que deseja alterar a senha");

		Cartao cartao = buscarCartao();

		if (cartao == null) {
			System.out.println("Cartão não encontrado!");
			return;
		}

		input.nextLine();

		System.out.println("Agora digite a senha antiga para poder redefinir");
		String senhaAntiga = input.nextLine();
		if (verificarSenha(cartao, senhaAntiga)) {
			System.out.println("Senha antiga correta");
			String novaSenha;

			do {
				System.out.println("Digite a nova senha do cartão:");
				novaSenha = input.nextLine();

				if (novaSenha == null || novaSenha.trim().isEmpty()) {
					System.out.println("A senha não pode estar vazia");
					return;
				}
				if (novaSenha.length() < 5) {
					System.out.println("A senha não pode ter menos de cinco digitos");
				}

				if (!validarSenha(novaSenha)) {
					System.out.println(
							"A senha deve conter pelo menos um caractere maiúsculo e um número. Tente novamente.");
				}
				// aqui eu continuo pedindo pro usuário digitar a senha até ser aceita
			} while (!validarSenha(novaSenha));

			cartao.alterarSenha(novaSenha);
			System.out.println("Senha do cartão alterada com sucesso! \n nova senha: " + novaSenha);
		} else {
			System.out.println("Senha incorreta. Não foi possível redefinir a senha");
		}
	}

	public void ListarCartoes() {
		List<Cartao> cartoes = cartaoDAO.getCartoes();

		if (cartoes.isEmpty()) {
			System.out.println("Não há nenhum cartão cadastrado");
			return;
		}
		for (Cartao cartao : cartoes) {
			System.out.println(cartao);
		}
	}

	public Cartao buscarCartao() {
		int id = input.nextInt();

		List<Cartao> cartoes = cartaoDAO.getCartoes();

		for (Cartao cartao : cartoes) {
			if (cartao.getId() == id) {
				return cartao;
			}
		}
		System.out.println("Cartão não encontrado");
		return null;
	}

	public void realizarPagamento() {

		System.out.println("Digite o ID do cartão que deseja realizar o pagamento: ");
		Cartao cartao = buscarCartao();

		if (cartao == null) {
			System.out.println("Cartão não encontrado");
			return;
		}

		System.out.println("Digite o valor do pagamento");
		double valor = input.nextDouble();

		if (cartao != null) {
			if (cartao instanceof CartaoCredito) {
				cartao.realizarPagamento(valor);
			} else if (cartao instanceof CartaoDebito) {
				cartao.realizarPagamento(valor);
			}
		}
	}
	
	public void alterarLimiteDiario() {
		
		System.out.println("Digite o ID do cartão que deseja alterar o limite diário: ");
		Cartao cartao = buscarCartao();
		
		if(cartao == null) {
			System.out.println("Cartão não existe");
			return;
		}
		
		if(cartao instanceof CartaoCredito) {
			System.out.println("Não existe limite diário para o cartão de crédito");
			return;
		}
		
		System.out.println("Digite o novo valor do limite diário do cartão de débito");
		double novoLimiteDiario = input.nextDouble();
		
		CartaoDebito cartaoDebito = (CartaoDebito) cartao;
		//"Olha, esse objeto cartao é na real um CartaoDebito, então trata ele como tal."
		//converte o objeto cartao pra um cartao de débito
		cartaoDebito.alterarLimiteDiario(novoLimiteDiario);
	}
	
	public void pagarFatura() {
		
		System.out.println("Digite o ID do cartão que deseja pagar a fatura: ");
		Cartao cartao = buscarCartao();
		
		if(cartao == null) {
			System.out.println("Cartão não existe");
			return;
		}
		
		if(cartao instanceof CartaoDebito) {
			System.out.println("Não é possível pagar a fatura de um cartão de débito, apenas crédito");
			return;
		}
		
		System.out.println("Digite o valor para pagar a fatura do cartão");
		double valor = input.nextDouble();
		
		//converte o cartão para CartaoCredito, eu sei que ele vai ser crédito, então faço o Cast
	    CartaoCredito cartaoCredito = (CartaoCredito) cartao;
		cartaoCredito.pagarFatura(valor);
	}

	public void infoCartao() {

		System.out.println("Digite o ID do cartão que deseja buscar");
		Cartao cartao = buscarCartao();

		if (cartao == null) {
			System.out.println("Não foi encontrado nenhum cartão");
		}

		Cliente cliente = cartao.getCliente(); // preciso pegar o cliente pra querer mostrar ele
		Conta conta = cartao.getConta();

		if (cartao instanceof CartaoCredito) {

			CartaoCredito cartaoCredito = (CartaoCredito) cartao; // aqui a mesma coisa

			System.out.println("\n--- INFORMAÇÕES DO CARTÃO DE CRÉDITO ---");
			System.out.println("Nome: " + cartao.getNome());
			System.out.println("ID: " + cartao.getId());
			System.out.println("Numéro do cartão: " + cartao.getNumCartao());
			System.out.println("Senha do cartão: " + cartao.getSenha());
			System.out.println("Tipo de cartão: " + cartao.getTipoDeCartao());
			System.out.println("Tipo de conta do titular: " + conta.getTipoDeConta());
			System.out.println("Tipo de cliente: " + cliente.getTipoDeCliente());
			System.out.println("Status do cartão: " + cartao.getStatusString());
			System.out.println("Limite do cartão de crédito: R$ " + cartaoCredito.getLimiteCredito());
			System.out.println("Saldo devedor: R$ " + cartaoCredito.getSaldoDevedor());
			System.out.println("--------------------------------\n");
		}

		if (cartao instanceof CartaoDebito) {

			CartaoDebito cartaoDebito = (CartaoDebito) cartao;

			System.out.println("\n--- INFORMAÇÕES DO CARTÃO DE DÉBITO ---");
			System.out.println("Nome: " + cartao.getNome());
			System.out.println("ID: " + cartao.getId());
			System.out.println("Numéro do cartão: " + cartao.getNumCartao());
			System.out.println("Senha do cartão: " + cartao.getSenha());
			System.out.println("Tipo de cartão: " + cartao.getTipoDeCartao());
			System.out.println("Tipo de conta do titular: " + conta.getTipoDeConta());
			System.out.println("Tipo de cliente: " + cliente.getTipoDeCliente());
			System.out.println("Status do cartão: " + cartao.getStatusString());
			System.out.println("Limite diário: R$ " + cartaoDebito.getLimiteDiario());
			System.out.println("--------------------------------\n");
		}
	}

	public ClienteService getClienteService() {
		return this.clienteService;
	}

	public boolean validarSenha(String senha) {
		// exige pelo menos uma letra maiúscula e um dígito
		String regex = "^(?=.*[A-Z])(?=.*\\d).+$";
		return senha.matches(regex);
	}
}
