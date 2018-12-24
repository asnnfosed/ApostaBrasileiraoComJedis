package usuario;

public class Endereco {

	private String pais;
	private String estado;
	private String cidade;
	private String rua;
	private String complemento;
	private String codigoPostal;
	
	
	/**
	 * @param pais
	 * @param estado
	 * @param cidade
	 * @param rua
	 * @param complemento
	 * @param codigoPostal
	 */
	public Endereco(String pais, String estado, String cidade, String rua, String complemento, String codigoPostal) {
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.rua = rua;
		this.complemento = complemento;
		this.codigoPostal = codigoPostal;
	}
	
	public Endereco(String pais, String estado, String cidade) {
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
	}
	
	public Endereco() {
		
	}
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	@Override
	public String toString() {
		return pais + ", " + estado + ", " + cidade + ", " + rua + ", " + complemento + ", " + codigoPostal;
	}
}
