package campeonato;

import java.util.List;

import redis.clients.jedis.Jedis;

public class Jogo {

	public static void main(String[] args) {

		Jedis jedis = new Jedis("localhost");

		System.out.println("Conexão com o servidor:");

		System.out.println("Execução do servidor: " + jedis.ping());
		
		jedis.flushDB();
		
		// Inserindo os dados em uma estrutura do tipo "string"
//		jedis.set("ChaveExemplo", "Criando o primeiro conjunto chave-valor !");
		
		// Recuperando os dados e mostrando na tela
		System.out.println("Lendo o valor guardado na chave : " + jedis.get("ChaveExemplo"));

		// Inserindo os dados em uma estrutura do tipo Lista "list"
		jedis.lpush("ListaExemplo", "Redis");
		jedis.lpush("ListaExemplo", "Aula");
		jedis.lpush("ListaExemplo", "Trabalho de BD");

		// Recuperando os dados e mostrando na tela
		List<String> list = jedis.lrange("ListaExemplo", 0, 100);

		for (int i = 0; i < list.size(); i++) {
			System.out.println("Valor guardado na lista : " + list.get(i));
		}

		// limpar server jedis.flushall();
		// limpar server jedis.flushdb();
	}
}
