package jedis;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import campeonato.Campeonato;
import campeonato.Partida;
import campeonato.Rodada;
import redis.clients.jedis.Jedis;
import usuario.Endereco;
import usuario.Genero;
import usuario.Usuario;

public class JedisManager {

	// Conecta no Redis do localhost
	public static Jedis jedis = new Jedis("localhost");

	// Registra dado no servidor Redis
	public static void gravarString(String chave, String valor) {
		jedis.set(chave, valor);
	}

	public static void salvaCampeonato(Campeonato campeonato) {

		// Remove os registros do campeonato anterior do Banco de Dados
//		jedis.flushDB();

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

				String chave = numRodada + "" + numPartida;
				// Salva os times e o resultado no Banco de dados
				jedis.lpush(chave, valores[2]);
				jedis.lpush(chave, valores[1]);
				jedis.lpush(chave, valores[0]);

				numPartida++;
			}
			numRodada++;
		}
	}

	// Carrega os dados armazenados no Banco de Dados e
	// retorna uma instaância de Campeonato com contendo as informações
	public static Campeonato carregaCampeonatoSalvo() {
		// Cria uma instância de Campeonato para receber
		// as informações extraídas do BD
		Campeonato campeonato = new Campeonato();

		// Lê as rodadas do campeonato
		for (int i = 0; i < Campeonato.getQuantidadeRodadas(); i++) {

			Rodada rodada = new Rodada();

			// Lê as partidas da rodada atual
			for (int j = 0; j < Rodada.getQuantidadePartidas(); j++) {
				String chave = i + "" + j;

				// Lista que recebe os Strings lidos do banco de dados
				List<String> listaDados = new ArrayList<String>();

				listaDados.add(jedis.lrange(chave, 0, 2).toString());

				// Separa cada String retornado pelo
				// servidor, o separa em 3 partes e
				// atribui cada um atributo de uma nova partida
				for (String stringPartida : listaDados) {

					// Array de Strings que recebe em cada índice
					// uma parte de um String retornado
					String[] arrayPartida = stringPartida.split(", ");

					// Atribui cada parte do Array a um String
					String time1 = arrayPartida[0].substring(1);
					String time2 = arrayPartida[1];
					String resultado = arrayPartida[2].substring(0, arrayPartida[2].length() - 1);

					// Atribui os Strings aos atributos de cada partida
					Partida partida = new Partida();
					partida.setNomeTime1(time1);
					partida.setNomeTime2(time2);
					partida.setResultado(resultado);

					// Adiciona a partida na rodada
					rodada.getPartidas().add(partida);

					// Imprime uma partida (apenas para testes)
//					System.out.println(
//							"Rodada " + (i + 1) + " | Partida " + (j + 1)
//							+ " Time 1: " + time1
//							+ " X Time 2: " + time2
//							+ " Resultado deu: " + resultado);
				}

			}

			// Adiciona uma rodada no campeonato
			campeonato.getRodadas().add(rodada);
		}

		// Retorna o campeonato
		return campeonato;
	}

	// Verifica se já existe o apelido no banco de dados
	public static Boolean verificaApelidoDisponivel(String apelido) {

		boolean disponivel = true;

		// Lista recebe dados do server em formato String,
		// usando o argumento do parâmetro como chave
		List<String> dadosUsuario = jedis.lrange(apelido, 0, 0);

		if (dadosUsuario.isEmpty()) {
			System.out.println("apelido " + apelido + " n�o existe");
			disponivel = true;
		} else {
			System.out.println("apelido " + apelido + " j� encontrado");
			disponivel = false;
		}

		return disponivel;
	}

	//Salva usuário no banco de dados
	public static void salvaUsuario(Usuario usuario) {

		String apelido = usuario.getApelido();

		jedis.rpush(apelido, usuario.getApelido());
		jedis.rpush(apelido, usuario.getNome());
		jedis.rpush(apelido, usuario.getNascimento().toString());
		jedis.rpush(apelido, usuario.getGenero().toString());
		jedis.rpush(apelido, usuario.getEndereco().toString());
		if (usuario.getPontuacao() != null) {
			jedis.rpush(apelido, usuario.getPontuacao().toString());
		}
		
		String idRanking = usuario.getApelido() + " " + usuario.getPontuacao();
		jedis.rpush("listaUsuarios", idRanking);
	}
	
	// Carrega a lista de usuarios salvos
	public static void carregarListaUsuarios () {
		List<String> listaUsuarios = jedis.lrange("listaUsuarios", 0, 380);
		
		Collections.sort(listaUsuarios);
		
		String usuario = null;
		String pontuacao = null;
		
		for (String string : listaUsuarios) {
			String[] strArray = string.split(" ");
			usuario = strArray[0];
			pontuacao = strArray[1];
		}
		
		System.out.println("Usuário: " + usuario + " | Pontuação: " + pontuacao);
	}
	
	// Apaga usuário do Banco de dados com base no apelido inserido
	public static void excluirUsuario(String apelido) {
		jedis.del(apelido);
	}
		
	// Carrega dados salvos de usuário salvo no server
	// e retornam uma instância de Usuario
	public static Usuario carregaUsuarioSalvo(String apelido) {
		// Lista recebe dados do server em formato String,
		// usando o argumento do parâmetro como chave
		List<String> dadosUsuario = jedis.lrange(apelido, 0, 5);

		// Instancia usuário que receberá as informações da lista
		Usuario usuario = new Usuario();

		/*
		 * Atribuição de cada item da lista para cada atributo do usuário criado
		 */

		// Atribuição do apelido
		if(!dadosUsuario.isEmpty()) {
			usuario.setApelido(dadosUsuario.get(0));

			// Atribuição do nome
			usuario.setNome(dadosUsuario.get(1));

			// Atribuição da data de nascimento
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Define formato da data
			String[] numData = dadosUsuario.get(2).split("-"); // Array de String que recebe cada parte da data (dia, mês e
																// ano)
			LocalDate d = LocalDate.parse(numData[2] + "/" + numData[1] + "/" + numData[0], dtf); // Instância de LocalDate
																									// que recebe os dados
																									// do Array
			usuario.setNascimento(d); // Atribui o LocalDate criado ao usu�rio
			// Atribui�Atribui��oão do G�nero
			usuario.setGenero(Genero.valueOf(dadosUsuario.get(3)));

			// Atribui��o�ão do endere�o
			String[] dadosEndereco = dadosUsuario.get(4).split(", ");
			Endereco endereco = new Endereco(dadosEndereco[0], dadosEndereco[1], dadosEndereco[2], dadosEndereco[3],
					dadosEndereco[4], dadosEndereco[5]);
			usuario.setEndereco(endereco);

			// Atribui��o da pontua��o
			usuario.setPontuacao(Integer.valueOf(dadosUsuario.get(5)));
		} else {
			System.out.println("Usu�rio inexistente.");
		}
		

		return usuario;
	}

}
