package campeonato;

import java.util.ArrayList;
import java.util.List;

public enum Times {

	// Enumera��o dos times
	AMERICA_MG, ATLETICO_MG, ATLETICO_PR, BAHIA, BOTAFOGO, CEAR�, CHAPECOENSE, CORINTHIANS, CRUZEIRO, FLAMENGO,
	FLUMINENSE, GR�MIO, INTERNACIONAL, PALMEIRAS, PARAN�, SANTOS, S�O_PAULO, SPORT, VASCO, VIT�RIA;

	// Vetor que carrega os times � partir do Enum Times
	private static Times[] arrayTimes = Times.values();

	// Lista que carrega os times armazenados no vetor como String
	private static List<String> listaTimes = new ArrayList<String>();

	// M�todo que popula a lista com os times do vetor
	// e retorna a lista de times
	public static List<String> carregaTimes() {

		for (int i = 0; i < arrayTimes.length; i++) {
			listaTimes.add(arrayTimes[i].toString());
		}

		return listaTimes;
	}
}
