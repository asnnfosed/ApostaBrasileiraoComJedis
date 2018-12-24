package campeonato;

import java.util.Random;

public class Partida {
	
	//Atributos
	private String nomeTime1;
	private String nomeTime2;
	private String resultado;

	//Define um resultado para a partida
	public void defineResultado() {
		//Array de resultados possíveis
		String[] resultadosPossiveis = {nomeTime1, nomeTime2, "EMPATE"};
		
		//Sorteio do resultado
		Random rand = new Random();
		int resultado = rand.nextInt(3);
		this.resultado = resultadosPossiveis[resultado];
	}
	
	//Getters e Setters
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public String getNomeTime1() {
		return nomeTime1;
	}
	public void setNomeTime1(String nomeTime1) {
		this.nomeTime1 = nomeTime1;
	}
	public String getNomeTime2() {
		return nomeTime2;
	}
	public void setNomeTime2(String nomeTime2) {
		this.nomeTime2 = nomeTime2;
	}
	
	
}
