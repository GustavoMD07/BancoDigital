package br.com.cdb.bancodigital.view;  

import br.com.cdb.bancodigital.DAO.CartaoDAO;
import br.com.cdb.bancodigital.DAO.ClienteDAO;
import br.com.cdb.bancodigital.DAO.ContaDAO;
import br.com.cdb.bancodigital.DAO.SeguroDAO;
import br.com.cdb.bancodigital.service.CartaoService;
import br.com.cdb.bancodigital.service.ClienteService;
import br.com.cdb.bancodigital.service.ContaService;
import br.com.cdb.bancodigital.service.SeguroService;

public class MainView {
	public static void main(String[] args) {
		
		ClienteDAO clienteDAO = new ClienteDAO();
		ContaDAO contaDAO = new ContaDAO(clienteDAO);
		CartaoDAO cartaoDAO = new CartaoDAO();
		SeguroDAO seguroDAO = new SeguroDAO();
	
		ClienteService clienteService = new ClienteService(clienteDAO);
		ContaService contaService = new ContaService(contaDAO, clienteDAO);
		CartaoService cartaoService = new CartaoService(cartaoDAO, contaService, clienteDAO);
		SeguroService seguroService = new SeguroService(seguroDAO, cartaoService, clienteService);

		ClienteView clienteView = new ClienteView(clienteService);
		ContaView contaView = new ContaView(contaService);
		CartaoView cartaoView = new CartaoView(cartaoService);
		SeguroView seguroView = new SeguroView(seguroService);
		MenuView menuView = new MenuView(clienteView, contaView, cartaoView, seguroView);

		clienteView.pegarMenuView(menuView);
		contaView.pegarMenuView(menuView);
		cartaoView.pegarMenuView(menuView);
		seguroView.pegarMenuView(menuView);

		menuView.iniciarMenu();

	}
}
