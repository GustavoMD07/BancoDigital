package br.com.cdb.bancodigital.view; //fazendo o view pra ser o visual do código, pra poder testar

public class MainView {
	public static void main(String[] args)  {
		
	ClienteView clienteView = new ClienteView();
	ContaView contaView = new ContaView();	
	CartaoView cartaoView = new CartaoView();	
	SeguroView seguroView = new SeguroView();	
	MenuView menuView = new MenuView(clienteView, contaView, cartaoView, seguroView);
		
		clienteView.pegarMenuView(menuView);
		contaView.pegarMenuView(menuView);
		cartaoView.pegarMenuView(menuView);
		seguroView.pegarMenuView(menuView);
		
		menuView.iniciarMenu();
		
	}
}
