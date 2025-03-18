package br.com.cdb.bancodigital.DAO;

import br.com.cdb.bancodigital.entity.*;
import java.util.ArrayList;
import java.util.List;

public class CartaoDAO {

	private ArrayList<Cartao> cartoes = new ArrayList<>();
	
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
	
	public void removerCartao(Cartao cartao) {
		cartoes.remove(cartao);
		System.out.println("Cartão removido com sucesso!.");
	}
	
	public List<Cartao> listarCartoes() {    //aqui ele retorna uma cópia da lista, pra evitar alteração na lista
		return new ArrayList<>(cartoes);   
	}
	
}
