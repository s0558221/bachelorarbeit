package bean;

/**
 * repraesentiert eine einzelne Quizantwort
 * @author Roy Beyer
 * @version 1.0
 */
public class Antwort {
	
	/**
	 * die Id der Antwort
	 */
	private Integer id;
	
	/**
	 * der Antwort-Text
	 */
	private String text;
	
	/**
	 * die Id der Frage, zu der die Anwort gehoert
	 */
	private Integer id_frage;
	
	/**
	 * ist die Antwort richtig oder falsch
	 */
	private boolean istKorrekt;
	
	/**
	 * Standardkonstruktor der Klasse Antwort
	 */
	public Antwort() {};
	
	/**
	 * der parametrisierte Konstruktor der Klasse Antwort
	 * @param id die Antwort-ID
	 * @param text der Antworttext
	 * @param id_frage die ID der dazugehoerigen Frage
	 * @param istKorrekt ob die Antwort korrekt ist
	 */
	public Antwort(Integer id, String text, Integer id_frage, boolean istKorrekt) {
		this.id = id;
		this.text = text;
		this.id_frage = id_frage;
		this.istKorrekt = istKorrekt;
	};
	
	/**
	 * gibt die ID der Antwort zurueck
	 * @return die Antwort-ID
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * setzt die Antwort-ID
	 * @param id die Antwort-ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * gibt den Antworttext zurueck
	 * @return der Antworttext
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * setzt den Antworttext
	 * @param text der Antworttext
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/**
	 * gibt die ID der dazugehoerigen Frage zurueck
	 * @return die ID der dazugehoerigen Frage
	 */
	public Integer getId_frage() {
		return id_frage;
	}
	
	/**
	 * setzt die ID der dazugehoerigen Frage
	 * @param id_frage die ID der dazugehoerigen Frage
	 */
	public void setId_frage(Integer id_frage) {
		this.id_frage = id_frage;
	}
	
	/**
	 * gibt zurueck, ob die Antwort korrekt ist
	 * @return ob Antwort korrekt ist
	 */
	public boolean getIstKorrekt() {
		return istKorrekt;
	}
	
	/**
	 * setzt, ob die Antwort korrekt ist oder nicht
	 * @param istKorrekt Korrektheit der Antwort
	 */
	public void setIstKorrekt(boolean istKorrekt) {
		this.istKorrekt = istKorrekt;
	}

}
