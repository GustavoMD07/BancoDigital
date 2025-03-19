package br.com.cdb.bancodigital.view;

import java.util.Scanner;

import br.com.cdb.bancodigital.DAO.SeguroDAO;
import br.com.cdb.bancodigital.service.SeguroService;

public class SeguroView {

	private MenuView menuView;
	private SeguroService seguroService;
	Scanner input;
	
	public SeguroView() {
		this.input = new Scanner(System.in);
		SeguroDAO seguroDAO = new SeguroDAO();
		this.seguroService = new SeguroService(seguroDAO);
	}
	
	public void pegarMenuView(MenuView menuView) {
		this.menuView = menuView; //to recebendo o objeto em vez de criar ele, li que é uma boa prática
	}
}
