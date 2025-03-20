package br.com.cdb.bancodigital.view;

import java.util.Scanner;
import br.com.cdb.bancodigital.service.CartaoService;
import br.com.cdb.bancodigital.DAO.CartaoDAO;

public class CartaoView {

	private MenuView menuView;
	private CartaoService cartaoService;
	private CartaoDAO cartaoDAO;
	Scanner input;

	public CartaoView(CartaoService cartaoService) {
		this.input = new Scanner(System.in);
		this.cartaoService = cartaoService;
	}

	public void pegarMenuView(MenuView menuView) {
		this.menuView = menuView;
	}

	public void menuCartaoPadrao() {
		System.out.println(" =====  MENU CARTÃO  ===== ");
		System.out.println("1 - Adicionar cartão ");
		System.out.println("2 - Listar cartões");
		System.out.println("3 - Buscar cartão");
		System.out.println("4 - Realizar pagamento");
		System.out.println("5 - Desativar cartão");
		System.out.println("6 - Ativar cartão");
		System.out.println("7 - Alterar senha do cartão");
		System.out.println("8 - Informações gerais");
		System.out.println("9 - Voltar ao menu principal");
	}

	public void iniciarMenuCartao() {
		boolean continuar = true;

		while (continuar) {
			menuView.limparConsole();
			menuCartaoPadrao();
			System.out.println("Selecione uma operação: ");
			int oper = input.nextInt();
			System.out.println();
			input.nextLine();

			switch (oper) {
			case 1:	
				cartaoService.addCartao();
				break;
			case 2:
				cartaoService.ListarCartoes();
				break;
			case 3:
				cartaoService.buscarCartao();
				break;
			case 4:
				cartaoService.realizarPagamento();
				break;
			case 5:
				cartaoService.desativarCartao();
				break;
			case 6:
				cartaoService.ativarCartao();
				break;
			case 7:
				cartaoService.alterarSenha();
				break;
			case 8:
				cartaoService.infoCartao();
				break;
			case 9:
				menuView.iniciarMenu();
				break;
			default:
				System.out.println("Operação inválida");
			}
		}
	}
}
