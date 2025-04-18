package br.com.cdb.bancodigital.DAO;

import br.com.cdb.bancodigital.entity.*;
import java.util.ArrayList;

public class CartaoDAO {

	private ArrayList<Cartao> cartoes = new ArrayList<>();

	public void addCartao(Cartao cartao) {
		cartoes.add(cartao);
		System.out.println("Cartão salvo com sucesso || Num: " + cartao.getNumCartao());
	}
	
	public Cartao buscarCartao(int id) {
		for (Cartao c : cartoes) {
			if(c.getId() == id) {
				return c;
			}
		}
		return null;
	}
	
	public void removerCartao(Cartao cartao) {
		cartoes.remove(cartao);
		System.out.println("Cartão removido com sucesso!.");
	}
	
	public ArrayList<Cartao> getCartoes() { //método gett pra colocar no view
		return new ArrayList<> (cartoes);
	}
	
}
