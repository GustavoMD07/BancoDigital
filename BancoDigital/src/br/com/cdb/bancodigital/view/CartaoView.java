package br.com.cdb.bancodigital.view;

import java.util.Scanner;
import br.com.cdb.bancodigital.service.CartaoService;

public class CartaoView {

	private MenuView menuView;
	private CartaoService cartaoService;
	Scanner input;

	public CartaoView() {
		this.input = new Scanner(System.in);
		this.cartaoService = new CartaoService();
	}

	public void pegarMenuView(MenuView menuView) {
		this.menuView = menuView;
	}

	public void menuCartaoPadrao() {
		System.out.println(" =====  MENU CARTÃO  ===== ");
		System.out.println("1 - ");
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

			}
		}
	}
}
