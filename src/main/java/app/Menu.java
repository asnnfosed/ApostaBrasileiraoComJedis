package app;

import exception.BrasileiraoException;
import util.Console;

public class Menu {

	public static final int CADASTRO_USUARIO = 1;
	public static final int APOSTA = 2;
	public static final int RANKING = 3;
	public static final int SAIR = 4;
	
	public int exibeOpcoes() throws BrasileiraoException {

	
			System.out.println("===MENU===");
			System.out.println("1 - Cadastro de usuário");
			System.out.println("2 - Aposta");
			System.out.println("3 - Ranking");
			System.out.println("4 - Sair");

			int opcao = Console.readInt();
			
			if(opcao < CADASTRO_USUARIO || opcao > RANKING) {
				throw new BrasileiraoException("Opção inválida");
			}
			
			return opcao;
	}
}
