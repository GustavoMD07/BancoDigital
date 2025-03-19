package br.com.cdb.bancodigital.view;

import java.util.Scanner;

import br.com.cdb.bancodigital.DAO.ClienteDAO;
import br.com.cdb.bancodigital.service.ClienteService;

public class ClienteView {

	private MenuView menuView;
	private ClienteService clienteService;
	Scanner input;
	
	public ClienteView() {
		this.input = new Scanner(System.in); //preciso usar o this pra me referenciar ao input de cima
		ClienteDAO clienteDAO = new ClienteDAO();
		this.clienteService = new ClienteService(clienteDAO);
	}
	
	public void pegarMenuView(MenuView menuView) {
		this.menuView = menuView;
	}
}
