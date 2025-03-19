package br.com.cdb.bancodigital.view;

import java.util.Scanner;

import br.com.cdb.bancodigital.DAO.ClienteDAO;
import br.com.cdb.bancodigital.DAO.ContaDAO;
import br.com.cdb.bancodigital.service.ContaService;

public class ContaView {

	private MenuView menuView;
	private ContaService contaService;
	Scanner input;
	
	public ContaView() {
		this.input = new Scanner(System.in);
		ClienteDAO clienteDAO = new ClienteDAO();
		ContaDAO contaDAO = new ContaDAO(clienteDAO);
		this.contaService = new ContaService(contaDAO); //complicação
	}
	
	public void pegarMenuView(MenuView menuView) {
		this.menuView = menuView;
	}
	
	public void menuContaPadrao() {
		System.out.println(" =====  MENU CONTA  ===== ");
	}
	
	public void iniciarMenuConta() {
		
	}
}
