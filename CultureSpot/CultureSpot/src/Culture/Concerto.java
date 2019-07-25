package Culture;

import java.io.Serializable;

public class Concerto implements Serializable {
	@Override
	public String toString() {
		return "Concerto [provincia=" + provincia + ", nome=" + nome + ", indirizzo=" + indirizzo + ", data=" + data
				+ "]";
	}

	private static final long serialVersionUID = 1L;
	String provincia;
	String nome;
	String indirizzo;
	String data;

	public Concerto() {
		provincia = " ";
		indirizzo = " ";
		nome = " ";
		data = " ";

	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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
