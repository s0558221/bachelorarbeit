package bean;

/**
 * repraesentiert eine einzelne Quiz-Frage
 * @author roy31
 * @version 1.0
 */
public class Frage {

	/**
	 * die Id der Frage
	 */
	private Integer id;
	
	/**
	 * der Fragen-Text
	 */
	private String text;
	
	/**
	 * die Id des Schwierigkeitsgrads
	 */
	private Integer schwierigkeit;
	
	/**
	 * die Id des Themengebiets
	 */
	private Integer thema;
	
	/**
	 * der Standardkonstruktor der Klasse Frage
	 */
	public Frage() {}
	
	/**
	 * der parametrisierte Konstruktor der Klasse Frage
	 * @param id die Frage-Id
	 * @param text der Frage-Text
	 * @param schwierigkeit die Id der Schwierigkeit
	 * @param thema die Id des Themengebiets
	 */
	public Frage(Integer id, String text, Integer schwierigkeit, Integer thema) {
		this.id = id;
		this.text = text;
		this.schwierigkeit = schwierigkeit;
		this.thema = thema;
	}
	
	/**
	 * gibt die Id des Themengebiets zurueck
	 * @return die Id des Themengebiets
	 */
	public Integer getThema() {
		return thema;
	}
	
	/**
	 * setzt die Id des Themengebiets
	 * @param thema die Id des Themengebiets
	 */
	public void setThema(Integer thema) {
		this.thema = thema;
	}

	/**
	 * gibt die Id der Frage zurueck
	 * @return die Id der Frage
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * setzt die Id der Frage
	 * @param id die Id der Frage
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * gibt den Fragentext zurueck
	 * @return den Fragentext
	 */
	public String getText() {
		return text;
	}

	/**
	 * setzt den Fragentext
	 * @param text der Fragentext
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * gibt die Id des Schwierigkeitsgrad zurueck
	 * @return die Id des Schwierigkeitsgrad
	 */
	public Integer getSchwierigkeit() {
		return schwierigkeit;
	}

	/**
	 * setzt die Id des Schwierigkeitsgrads
	 * @param schwierigkeit die Id des Schwierigkeitsgrad
	 */
	public void setSchwierigkeit(Integer schwierigkeit) {
		this.schwierigkeit = schwierigkeit;
	}
}
