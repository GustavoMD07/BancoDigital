package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.DAO.CartaoDAO;
import br.com.cdb.bancodigital.entity.Cartao;


public class CartaoService {

	CartaoDAO cartaoDAO = new CartaoDAO();

	private boolean verificarSenha(Cartao cartao, String senha) {
		return cartao.getSenha().equals(senha); // método de verificação de senha para poder prosseguir
	}
	
	public void addCartao(Cartao cartao) {
		try {
			cartaoDAO.addCartao(cartao);
		}
		catch (Exception e) {
			System.out.println("Erro ao adicionar cartão: " + e.getMessage());
		}
	}
	
	public void removerCartao(Cartao cartao) {
		try {
			cartaoDAO.removerCartao(cartao);
		}
		catch (Exception e) {
			System.out.println("Erro ao remover cartão: " + e.getMessage());
		}
	}

	public void ativarCartao(Integer id, String senha) {
		Cartao cartao = cartaoDAO.buscarCartao(id);
		if (cartao == null) {
			System.out.println("Cartão não encontrado!.");
			return;
		}
		if (verificarSenha(cartao, senha)) {
			cartao.ativar();
		} else {
			System.out.println("Senha incorreta, não foi possível ativar o cartão");
		}
	}

	public void desativarCartao(Integer id, String senha) {
		Cartao cartao = cartaoDAO.buscarCartao(id);
		if (cartao == null) {
			System.out.println("Cartão não encontrado");
			return;
		}
		
		if (verificarSenha(cartao, senha)) {
			cartao.desativar();
		} 
		else {
			System.out.println("Senha incorreta, não foi possível desativar o cartão");
		}
	}

	public void alterarSenha(Integer id, String senhaAntiga, String novaSenha) {
		Cartao cartao = cartaoDAO.buscarCartao(id);
		if (cartao == null) {
			System.out.println("Cartão não encontrado!");
			return;
		}
		
		if (verificarSenha(cartao, senhaAntiga)) {
			cartao.alterarSenha(novaSenha);
			System.out.println("Senha do cartão alterada com sucesso! \n nova senha: " + novaSenha);
		} 
		else {
			System.out.println("Senha incorreta. Não foi possível redefinir a senha");
		}
	}

}
