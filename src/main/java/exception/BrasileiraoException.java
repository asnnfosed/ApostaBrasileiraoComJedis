package exception;

	/**
	 * Responável pelas exceções da aplicação
	 * 
	 * 
	 *
	 */
	@SuppressWarnings("serial")
	public class BrasileiraoException extends Exception {

		/**
		 * Retorna mensagem com detalhe da exceção
		 * @param message
		 */
		public BrasileiraoException(String message) {
			super(message);
		}
	}

