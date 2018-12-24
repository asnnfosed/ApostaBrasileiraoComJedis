package campeonato;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rodada {
	
	// Partidas por rodada
	public static final int QUANTIDADE_PARTIDAS = 10;

	// Lista de todos os times competidores
	private List<String> times = Times.carregaTimes();

	// Lista que recebe as partidas da rodada
	private List<Partida> partidas = new ArrayList<Partida>();

	// Executa a rodada de partidas
	public void executaRodada() {
		for (int i = 0; i < QUANTIDADE_PARTIDAS; i++) {
			executaPartida();
		}
	}

	/*
	 * Instancia uma nova partida, sorteia os times da partida, calcula o resultado
	 * e adiciona a partida na lista de partidas da rodada.
	 */
	private void executaPartida() {
		Partida partida = new Partida();
		partida.setNomeTime1(sorteiaTime());
		partida.setNomeTime2(sorteiaTime());

		partida.defineResultado();

		partidas.add(partida);
	}

	// Método que sorteia um time para uma partida
	private String sorteiaTime() {
		Collections.shuffle(times); // mistura a ordem dos times
		String timeSorteado = times.get(0); // Recebe o time sorteado
		times.remove(0); // Remove o time sorteado da lista de sorteio para evitar repetição
		return timeSorteado;
	}
	
	// Imprime as partidas da rodada
	public void imprimeRodada() {
		
		int numJogo = 1;
		for (Partida partida : partidas) {
			System.out.println("Partida " + numJogo + " | " + partida.getNomeTime1() + " X " + partida.getNomeTime2()
			+ " | Vencedor -> " + partida.getResultado());
			numJogo++;
		}
	}

	/*
	 * Métodos getters e setters
	 */
	public List<String> getTimes() {
		return times;
	}

	public void setTimes(List<String> times) {
		this.times = times;
	}

	public List<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(List<Partida> partidas) {
		this.partidas = partidas;
	}

	public static int getQuantidadePartidas() {
		return QUANTIDADE_PARTIDAS;
	}
}
