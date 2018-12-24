package usuario;

import java.util.List;

import app.Aplicacao;
import app.Menu;
import campeonato.Campeonato;
import campeonato.Partida;
import campeonato.Rodada;
import exception.BrasileiraoException;
import jedis.JedisManager;
import util.Console;

public class Aposta {

	// Recebe a pontuação total obtida com a aposta
	private int pontuacao;

	// Recebe a instância de uma campeonato
	private Campeonato campeonatoApostado;

	// Sorteia times e resultados dos jogos de uma aposta
	public Campeonato criaAposta() {

		campeonatoApostado = new Campeonato();

		campeonatoApostado.executaCampeonato();

		return campeonatoApostado;
	}

	// Compara campeonato do Banco de dados com o da aposta
	// e calcula a pontuação obtida
	public int calculaPontuacao(Campeonato campeonato, Campeonato aposta) {

		// Recebe a lista de rodadas do campeonato passado como argumento
		List<Rodada> rodadas = campeonato.getRodadas();

		int numRodada = 0;
		// Lê as rodadas do campeonato
		for (Rodada rodada : rodadas) {

			// Recebe a lista de partidas da rodada atual
			List<Partida> partidas = rodada.getPartidas();

			int numPartida = 0;
			// Lê as partidas da rodada atual
			for (Partida partida : partidas) {

				// Recebe resulrado das partidas atuais
				String resultado = partida.getResultado();

				String resultadoApostado = aposta.getRodadas().get(numRodada).getPartidas().get(numPartida)
						.getResultado();

				if (resultado.equals(resultadoApostado)) {
					pontuacao++;
				}
				numPartida++;
			}
			numRodada++;
		}
		return pontuacao;
	}

	public void criaCampeonatoDaAposta(String apelido, Campeonato campeonato) throws BrasileiraoException {

		Usuario usuario = JedisManager.carregaUsuarioSalvo(apelido);

		// Recebe a lista de rodadas do campeonato passado como argumento
		List<Rodada> rodadas = campeonato.getRodadas();

		int numRodada = 0;
		// Lê as rodadas do campeonato
		for (Rodada rodada : rodadas) {

			// Recebe a lista de partidas da rodada atual
			List<Partida> partidas = rodada.getPartidas();

			int numPartida = 0;
			// Lê as partidas da rodada atual
			for (Partida partida : partidas) {

				// Recebe resulrado das partidas atuais
				String[] valores = { partida.getNomeTime1(), partida.getNomeTime2(), partida.getResultado() };

				System.out.println("Insira o resultado conforme as opções");

				System.out.println("Rodada " + numRodada + " |Opções -> 1: " + partida.getNomeTime1() + ", 2: " + partida.getNomeTime2() + ", 3: Empate");
				System.out.println("-> ");

				int opcao = Console.readInt();

				String resultado = null;

				switch (opcao) {
				case 1:
					resultado = valores[0];
					if (resultado.equals(partida.getResultado())) {
						pontuacao++;
						usuario.setPontuacao(pontuacao);
						JedisManager.salvaUsuario(usuario);
					}
					break;
				case 2:
					resultado = valores[1];
					if (resultado.equals(partida.getResultado())) {
						pontuacao++;
						usuario.setPontuacao(pontuacao);
						JedisManager.salvaUsuario(usuario);
					}
					break;
				case 3:
					resultado = valores[2];
					if (resultado.equals(partida.getResultado())) {
						pontuacao++;
						usuario.setPontuacao(pontuacao);
						JedisManager.salvaUsuario(usuario);
					}
					break;
				default:
					System.out.println("Jogo salvo!");
					usuario.setPontuacao(pontuacao);
					JedisManager.excluirUsuario(apelido); // Exclui o usu�rio que possui pontuação antiga
					JedisManager.salvaUsuario(usuario); // Salva o usuário com pontuação nova

					System.out.println("Jogo encerrado.");
					Aplicacao.iniciar();
				}

			}
			numPartida++;
		}
		numRodada++;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public Campeonato getCampeonatoApostado() {
		return campeonatoApostado;
	}

	public void setCampeonatoApostado(Campeonato campeonatoApostado) {
		this.campeonatoApostado = campeonatoApostado;
	}
}
