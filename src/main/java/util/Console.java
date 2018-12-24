package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import exception.BrasileiraoException;

/**
 * Classe que atua na leitura e escrita
 * de dados no console.
 * 
 * 
 *
 */
public class Console {

	/**
	 * L� entrada do console
	 * 
	 * @return : String com linha digitada
	 */
	public static String readString() throws BrasileiraoException {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine();
		} catch (IOException e) {
			throw new RuntimeException ("Erro ao ler teclado");
		}
	}
	
	/**
	 * L� entrada do console
	 * 
	 * @return : char no in�cio da linha digitada
	 */
	public static char readChar() throws BrasileiraoException {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			return br.readLine().charAt(0);
		} catch (IOException e) {
			throw new BrasileiraoException ("Erro ao ler teclado");
		} catch (StringIndexOutOfBoundsException e) {
			throw new BrasileiraoException("Alguma letra deve ser digitada");
		}
	}
	
	/**
	 * L� um valor do tipo int no console
	 * 
	 * @return : int lido do teclado.
	 */
	public static int readInt () throws BrasileiraoException {
		String str = readString();
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {
			throw new BrasileiraoException ("Erro ao ler teclado: " + str + " n�o � um int v�lido.");
		}
	}
}
