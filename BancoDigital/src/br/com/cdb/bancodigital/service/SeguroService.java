package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.DAO.SeguroDAO;
import br.com.cdb.bancodigital.entity.*;


public class SeguroService {

	private SeguroDAO seguroDAO;
	
	public SeguroService(SeguroDAO seguroDAO) {
		this.seguroDAO = seguroDAO;
	}

	public Seguro seguroViagem(CartaoCredito cartao, Cliente cliente) {
		double valorApolice = 0.0;
		String condicoes = ""; //bug de inicialização
		
		if(cartao == null || cliente == null) {
			System.out.println("Cliente ou cartão não encontrado.");
			return null; 
		}
		
		if(cliente instanceof ClienteComum || cliente instanceof ClienteSuper ) {
			valorApolice = 50.0;
			condicoes = "Cobertura para viagens internacionais acionada. Taxa mensal: R$ " + valorApolice;
		}
		
		if(cliente instanceof ClientePremium) {
			valorApolice = 0.0;
			condicoes = "Cobertura para viagens internacionais acionada. Gratuito para clientes Premium :)";
		}
		
		Seguro seguro = new Seguro(cartao, valorApolice, condicoes);
		seguroDAO.addSeguro(seguro);
		return seguro;
	}
	
	public Seguro seguroFraude(CartaoCredito cartao, Cliente cliente) {
		
		if(cartao == null || cliente == null) {
			System.out.println("Cliente ou cartão não encontrado.");
			return null;
		}
		double valorApolice = 5000.0;
		String condicoes = "Cobertura contra fraudes acionada. Valor de até R$ " + valorApolice;
		Seguro seguro = new Seguro(cartao, valorApolice, condicoes);
		seguroDAO.addSeguro(seguro);
		return seguro;
	}
	
	public void adicionarSeguroAoCartao(CartaoCredito cartao, Cliente cliente) {
		
		if(cartao == null || cliente == null) {
			System.out.println("Cliente ou cartão não encontrado.");
			return;
		}
		Seguro seguroFraude = seguroFraude(cartao, cliente); //aqui eu chamo o método e atribuo o valor a varíavel
		
		if(seguroFraude != null) {
			cartao.addSeguro(seguroFraude); //o parâmetro é o seguro, então eu adiciono o seguro fraude
		}
		
		Seguro seguroViagem = seguroViagem(cartao, cliente);
		
		if(seguroFraude != null) {
			cartao.addSeguro(seguroViagem);
		}
		
		if(cliente instanceof ClientePremium) {
			System.out.println("Oferecendo seguro viagem gratuito ao cliente Premium...");
		}
		else {
			System.out.println("Oferecendo seguro viagem ao cliente...");
		}
	}
	
	public void listarSeguros() {
		for(Seguro seguro: seguroDAO.listarSeguros()) {
			System.out.println(seguro);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
