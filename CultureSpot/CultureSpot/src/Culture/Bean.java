package Culture;
import java.io.Serializable;

public class Bean implements Serializable {
	private static final long serialVersionUID = 1L;
	String comune;
	String provincia;
	String regione;
	String nome;
	String address;
	String data;
	String type;
	float longi;
	float lati;

	public Bean() {
		comune = "";
		provincia = "";
		regione ="";
		address = "";
		nome = "";
		data = "";
		longi = 0;
		lati = 0;
		type="";

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public float getLongi() {
		return longi;
	}

	public void setLongi(float longi) {
		this.longi = longi;
	}

	public float getLati() {
		return lati;
	}

	public void setLati(float lati) {
		this.lati = lati;
	}

	@Override
	public String toString() {
		return "Bean [comune=" + comune + ", provincia=" + provincia + ", regione=" + regione + ", nome=" + nome
				+ ", address=" + address + ", data=" + data + ", type=" + type + ", longi=" + longi + ", lati=" + lati
				+ "]";
	}




}

