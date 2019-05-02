package Culture;

import java.io.Serializable;

public class TheatreBean implements Serializable {
	private static final long serialVersionUID = 1L;

	String spettacolo;
	String regia;
	String data;
	String autore;
	String protagonista;
	String produttore;
	String locandina;
	String nometeatro;
	
	public TheatreBean(){
		spettacolo = "";
		regia ="";
		protagonista="";
		data = "";
		produttore ="";
		locandina="";
		nometeatro="";
	}

	public String getSpettacolo() {
		return spettacolo;
	}

	public void setSpettacolo(String spettacolo) {
		this.spettacolo = spettacolo;
	}

	public String getRegia() {
		return regia;
	}

	public void setRegia(String regia) {
		this.regia = regia;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getProtagonista() {
		return protagonista;
	}

	public void setProtagonista(String protagonista) {
		this.protagonista = protagonista;
	}

	public String getProduttore() {
		return produttore;
	}

	public void setProduttore(String produttore) {
		this.produttore = produttore;
	}

	public String getLocandina() {
		return locandina;
	}

	public void setLocandina(String locandina) {
		this.locandina = locandina;
	}

	public String getNometeatro() {
		return nometeatro;
	}

	public void setNometeatro(String nometeatro) {
		this.nometeatro = nometeatro;
	}

	@Override
	public String toString() {
		return "TheatreBean [spettacolo=" + spettacolo + ", regia=" + regia + ", data=" + data + ", autore=" + autore
				+ ", protagonista=" + protagonista + ", produttore=" + produttore + ", locandina=" + locandina
				+ ", nometeatro=" + nometeatro + "]";
	}
}