package br.com.cdb.bancodigital.view;

import java.util.Scanner;

import br.com.cdb.bancodigital.DAO.ClienteDAO;
import br.com.cdb.bancodigital.DAO.SeguroDAO;
import br.com.cdb.bancodigital.service.SeguroService;
import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.view.ClienteView;
import br.com.cdb.bancodigital.service.ClienteService;

public class SeguroView {

	private MenuView menuView;
	private SeguroService seguroService;
	Scanner input;

	public SeguroView(SeguroService seguroService) {
		this.input = new Scanner(System.in);
		this.seguroService = seguroService;
	}

	public void pegarMenuView(MenuView menuView) {
		this.menuView = menuView; // to recebendo o objeto em vez de criar ele, li que é uma boa prática
	}

	public void menuSeguroPadrao() {
		System.out.println(" =====  MENU SEGUROS  ===== ");
		System.out.println("1 - Aplicar e associar seguro viagem");
		System.out.println("2 - Aplicar e associar seguro fraude");
		System.out.println("3 - Listar seguros");
		System.out.println("4 - Exibir detalhes");
		System.out.println("5 - Voltar ao menu principal");
	}

	public void iniciarMenuSeguros() {
		boolean continuar = true;

		while (continuar) {
			menuView.limparConsole();
			menuSeguroPadrao();
			System.out.println("Selecione uma operação: ");
			int oper = input.nextInt();
			System.out.println();
			input.nextLine();

			switch (oper) {
			case 1:
				seguroService.seguroViagem();
				break;
			case 2:
				seguroService.seguroFraude();
				break;
			case 3:
				seguroService.listarSeguros();
				break;
			case 4:
				seguroService.infoApolice();
				break;
			case 5:
				menuView.iniciarMenu();
				break;
			default:
				System.out.println("Operação inválida.");
			}
		}
	}
}
