package br.com.cdb.bancodigital.view;

import java.util.Scanner;
import br.com.cdb.bancodigital.service.ContaService;

public class ContaView {

	private MenuView menuView;
	private ContaService contaService;
	Scanner input;

	public ContaView(ContaService contaService) {
		this.input = new Scanner(System.in);
		this.contaService = contaService;
	}

	public void pegarMenuView(MenuView menuView) {
		this.menuView = menuView;
	}

	public void menuContaPadrao() {
		System.out.println(" =====  MENU CONTA  ===== ");
		System.out.println("1 - Criar conta");
		System.out.println("2 - Transferência bancária");
		System.out.println("3 - Depósito");
		System.out.println("4 - Saque");
		System.out.println("5 - Informações da conta");
		System.out.println("6 - Voltar ao menu principal");
	}

	public void iniciarMenuConta() {
		boolean continuar = true;

		while (continuar) {
			menuView.limparConsole();
			menuContaPadrao();
			System.out.println("Selecione uma operação: ");
			int oper = input.nextInt();
			System.out.println();
			input.nextLine();

			switch (oper) {
			case 1:
				contaService.adicionarConta();
				break;
			case 2:
				contaService.transferencia();
				break;
			case 3:
				contaService.depositar();
				break;
			case 4:
				contaService.sacar();
				break;
			case 5:
				contaService.infoConta();
				break;
			case 6:
				menuView.iniciarMenu();
				break;
			default:
				System.out.println("Operação inválida!");
			}
		}
	}
}
