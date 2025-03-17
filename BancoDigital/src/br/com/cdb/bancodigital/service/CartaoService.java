package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.DAO.CartaoDAO;
import br.com.cdb.bancodigital.entity.Cartao;
import br.com.cdb.bancodigital.entity.CartaoCredito;
import br.com.cdb.bancodigital.entity.CartaoDebito;

public class CartaoService {

	CartaoDAO cartaoDao = new CartaoDAO();

	private boolean verificarSenha(Cartao cartao, String senha) {
		return cartao.getSenha().equals(senha); // método de verificação de senha para poder prosseguir
	}

	public boolean ativarCartao(Integer id, String senha) {
		Cartao cartao = cartaoDao.buscarCartao(id);
		if (cartao != null) {
			if (verificarSenha(cartao, senha)) {
				return cartao.ativar();
			} else {
				System.out.println("Senha incorreta, não foi possível ativar o cartão");
			}
		}
		System.out.println("Cartão não encontrado");
		return false;
	}
	
	public boolean desativarCartao(Integer id, String senha) {
		Cartao cartao = cartaoDao.buscarCartao(id);
		if (cartao != null) {
			if (verificarSenha(cartao, senha)) {
				return cartao.desativar();
			} else {
				System.out.println("Senha incorreta, não foi possível desativar o cartão");
			}
		}
		System.out.println("Cartão não encontrado");
		return false;
	}
	
	public void alterarSenha(Integer id, String senhaAntiga, String novaSenha) {
		Cartao cartao = cartaoDao.buscarCartao(id);
		if(cartao != null) {
			if (verificarSenha(cartao, senhaAntiga)) {
				cartao.alterarSenha(novaSenha);
				System.out.println("Senha do cartão alterada com sucesso! \n nova senha: " + novaSenha);
			}
			else {
				System.out.println("Senha incorreta. Não foi possível redefinir a senha");
			}
		}
		else {
			System.out.println("Cartão não encontrado!");
		}
	}
	
}
