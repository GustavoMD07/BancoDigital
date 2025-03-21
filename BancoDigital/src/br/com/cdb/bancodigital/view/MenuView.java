package br.com.cdb.bancodigital.view;

import java.util.Scanner;

public class MenuView {
	Scanner input;
	
	private ClienteView clienteView;
    private ContaView contaView;
    private CartaoView cartaoView;
    private SeguroView seguroView;


	public MenuView(ClienteView clienteView, ContaView contaView, CartaoView cartaoView, SeguroView seguroView) {
		input = new Scanner(System.in); // boa prática criar o scanner dentro do método construtor
		this.clienteView = clienteView;
		this.contaView = contaView;
		this.cartaoView = cartaoView;
		this.seguroView = seguroView;
	}

	public void iniciarMenu() {

		boolean continuar = true;

		while (continuar) {
			System.out.println(" =====  DenisBank  ===== ");
			System.out.println("1 - Acessar Menu Cliente\n");
			System.out.println("2 - Acessar Menu Conta\n");
			System.out.println("3 - Acessar Menu Cartão\n");
			System.out.println("4 - Acessar Menu Seguros\n");
			System.out.println("5 - Sair do Sistema\n");

			System.out.println("Escolha uma operação: ");
			int oper = input.nextInt();

			switch (oper) {
			case 1:
				clienteView.iniciarMenuCliente();
				break;
			case 2:
				contaView.iniciarMenuConta();
				break;
			case 3:
				cartaoView.iniciarMenuCartao();
				break;
			case 4:
				seguroView.iniciarMenuSeguros();
				break;
			case 5:
				sairSistema();
				continuar = false;
				break;
			default:
				System.out.println("Operação inválida!");
				sairSistema();
				continuar = false;
			}
		}
	}

	private void sairSistema() {
		System.out.println("Saindo do sistema. Obrigado por usar o DenisBank"); // essa é pra você Denilson
	}

	protected void limparConsole() { // aqui ele ""limpa"" o console pulando 50 linhas
		System.out.println("\n".repeat(3));
	}
}
