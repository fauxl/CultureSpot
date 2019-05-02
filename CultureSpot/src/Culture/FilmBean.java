package Culture;

import java.io.Serializable;

public class FilmBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	String film;
	String regia;
	int anno;
	String durata;
	String genere;
	String locandina;
	String nomecinema;
	
	public FilmBean(){
		film = "";
		regia ="";
		anno = 0;
		durata ="";
		genere="";
		locandina="";
		nomecinema="";
	}

	public String getFilm() {
		return film;
	}

	public void setFilm(String film) {
		this.film = film;
	}

	public String getRegia() {
		return regia;
	}

	public void setRegia(String regia) {
		this.regia = regia;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public String getDurata() {
		return durata;
	}

	public void setDurata(String durata) {
		this.durata = durata;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}

	public String getLocandina() {
		return locandina;
	}

	public void setLocandina(String locandina) {
		this.locandina = locandina;
	}

	public String getNomecinema() {
		return nomecinema;
	}

	public void setNomecinema(String nomecinema) {
		this.nomecinema = nomecinema;
	}
	
	@Override
	public String toString() {
		return "FilmBean [film=" + film + ", regia=" + regia + ", anno=" + anno + ", durata=" + durata + ", genere="
				+ genere + ", locandina=" + locandina + ", nomecinema=" + nomecinema + "]";
	}
	
	
}
