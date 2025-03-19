package br.com.cdb.bancodigital.DAO;

import br.com.cdb.bancodigital.entity.Seguro;
import java.util.ArrayList;
import java.util.List;

public class SeguroDAO {

	private ArrayList<Seguro> seguros = new ArrayList<>();

	public void addSeguro(Seguro seguro) {
		seguros.add(seguro);
		System.out.println("Seguro adicionado com sucesso: #" + seguro.getNumeroApolice());	
	}
	
	public List<Seguro> listarSeguros() {
		return new ArrayList<Seguro>(seguros); //cópia da lista
	}
	
	public Seguro buscarSeguroPorNumeroApolice(String numApolice) {
		for (Seguro seguro : seguros) {
			if(seguro.getNumeroApolice().equals(numApolice)) {
				return seguro;
			}
		}
		return null;
	}
	
	public ArrayList<Seguro> getSeguros() {
		return seguros;
	}
}
