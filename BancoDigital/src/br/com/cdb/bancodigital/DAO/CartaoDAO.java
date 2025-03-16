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
	
	public Cartao buscarCartao(double numCartao) {
		for (Cartao c : cartoes) {
			if(c.getNumCartao() == numCartao) {
				return c;
			}
		}
		return null;
	}
	
	public void removerCartao(double numCartao) {
		boolean removido = cartoes.removeIf(cartao -> cartao.getNumCartao() == numCartao);
		if(removido) {
			System.out.println("Cartão de número " + numCartao + " removido com sucesso!");
		}
		else { System.out.println("Não foi possível encontrar o cartão"); }
	}
	
	public List<Cartao> listarTodos() {    //aqui ele retorna uma cópia da lista, pra evitar que 
		return new ArrayList<>(cartoes);   //a lista seja modificada
	}
}
