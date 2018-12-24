package campeonato;

import java.util.ArrayList;
import java.util.List;

public enum Times {

	// Enumeração dos times
	AMERICA_MG, ATLETICO_MG, ATLETICO_PR, BAHIA, BOTAFOGO, CEARÁ, CHAPECOENSE, CORINTHIANS, CRUZEIRO, FLAMENGO,
	FLUMINENSE, GRÊMIO, INTERNACIONAL, PALMEIRAS, PARANÁ, SANTOS, SÃO_PAULO, SPORT, VASCO, VITÓRIA;

	// Vetor que carrega os times à partir do Enum Times
	private static Times[] arrayTimes = Times.values();

	// Lista que carrega os times armazenados no vetor como String
	private static List<String> listaTimes = new ArrayList<String>();

	// Método que popula a lista com os times do vetor
	// e retorna a lista de times
	public static List<String> carregaTimes() {

		for (int i = 0; i < arrayTimes.length; i++) {
			listaTimes.add(arrayTimes[i].toString());
		}

		return listaTimes;
	}
}
