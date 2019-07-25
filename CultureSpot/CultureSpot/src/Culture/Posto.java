package Culture;
import java.io.Serializable;

public class Posto implements Serializable {
	@Override
	public String toString() {
		return "Posto [tipologia=" + tipologia + ", provincia=" + provincia + ", nome=" + nome + ", indirizzo="
				+ indirizzo + "]";
	}

	private static final long serialVersionUID = 1L;
	String tipologia;
	String provincia;
	String nome;
	String indirizzo;

	public Posto() {
		tipologia= " ";
		provincia = " ";
		indirizzo = " ";
		nome = " ";
	}
	
	public String getTipologia() {
		return tipologia;
	}

	public void setTipologia(String tipo) {
		this.tipologia = tipo;
	}

	

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String address) {
		this.indirizzo = address;
	}


	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



}


