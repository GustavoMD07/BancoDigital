package br.com.cdb.bancodigital.DAO;

import br.com.cdb.bancodigital.entity.*;
import java.util.ArrayList;
import java.util.List;

public class CartaoDAO {

	private List<Cartao> cartoes = new ArrayList<>();
	
	public void addCartao(Cartao cartao) {
		cartoes.add(cartao);
		System.out.println("Cartão salvo com sucesso || Num: " + cartao.getNumCartao());
	}
	
	public Cartao buscarCartao(int id) {
		for (Cartao c : cartoes) {
			if(c.getId().equals (id)) {
				return c;
			}
		}
		return null;
	}
	
	public void removerCartao(int id) {
		boolean removido = cartoes.removeIf(cartao -> cartao.getId() == id);
		if(removido) {
			System.out.println("Cartão de ID: " + id + " removido com sucesso!");
		}
		else { System.out.println("Não foi possível encontrar o cartão!"); }
	}
	
	public List<Cartao> listarCartoes() {    //aqui ele retorna uma cópia da lista, pra evitar alteração na lista
		return new ArrayList<>(cartoes);   
	}
}
