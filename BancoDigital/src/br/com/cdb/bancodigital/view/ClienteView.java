package br.com.cdb.bancodigital.view;

import java.util.Scanner;

import br.com.cdb.bancodigital.service.ClienteService;

public class ClienteView {

	private MenuView menuView;
	private ClienteService clienteService;
	Scanner input;

	public ClienteView(ClienteService clienteService) {
		this.input = new Scanner(System.in); // preciso usar o this pra me referenciar ao input de cima
		this.clienteService = clienteService;
		}

	public void pegarMenuView(MenuView menuView) {
		this.menuView = menuView;
	}

	public void menuClientePadrao() {
		System.out.println(" =====  MENU CLIENTE ===== ");
		System.out.println("1 - Cadastrar cliente");
		System.out.println("2 - Buscar cliente por CPF");
		System.out.println("3 - Lista de clientes");
		System.out.println("4 - Verificar informações de cliente");
		System.out.println("5 - Voltar ao menu principal");
	}

	public void iniciarMenuCliente() {
		boolean continuar = true;

		while (continuar) {
			// menuView.limparConsole();
			menuClientePadrao();
			System.out.println("Escolha uma operação: ");
			String operStr = input.nextLine().trim(); // tava bugando pra ler, ai eu pedi pra ler como String
			int oper = Integer.parseInt(operStr); // depois converter pra int e ai poder continuar

			switch (oper) {
			case 1:
				clienteService.adicionarCliente();
				break;
			case 2:
				clienteService.buscarClientePorCpf();
				break;
			case 3:
				clienteService.listarClientes();
				break;
			case 4:
				clienteService.infoCliente();
				break;
			case 5:
				menuView.iniciarMenu();
				break;
			default:
				System.out.println("Opção inválida!");
			}

		}
	}
}
