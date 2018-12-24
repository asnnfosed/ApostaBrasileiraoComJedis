package campeonato;

import java.util.ArrayList;
import java.util.List;

public class Campeonato {

	// Rodadas por campeonato
	private static final int QUANTIDADE_RODADAS = 38;

	// Lista de rodadas do campeonato
	private List<Rodada> rodadas = new ArrayList<Rodada>();

	// Executa todas as rodadas do campeonato
	public void executaCampeonato() {
		for (int i = 0; i < QUANTIDADE_RODADAS; i++) {
			Rodada rodada = new Rodada(); // Instancia a rodada
			rodada.executaRodada(); // Executa a rodada
			rodadas.add(rodada); // Adiciona rodada na lista de rodadas da partida
		}
	}
	
	//Imprime as rodadas do campeonato no console
	public void imprimeCampeonato() {
		int numRodada = 1;
		for(Rodada rodada : rodadas) {
			System.out.println("Rodada " + numRodada + " =========");
			rodada.imprimeRodada();
			numRodada++;
		}
	}
	
	/*
	 * Métodos getters e setters
	 */
	public List<Rodada> getRodadas() {
		return rodadas;
	}

	public void setRodadas(List<Rodada> rodadas) {
		this.rodadas = rodadas;
	}

	public static int getQuantidadeRodadas() {
		return QUANTIDADE_RODADAS;
	}
}
