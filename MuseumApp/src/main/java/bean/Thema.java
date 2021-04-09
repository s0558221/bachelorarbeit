package bean;

/**
 * repraesemtiert das Themengebiet einer Frage
 * @author Roy Beyer
 * @version 1.0
 */
public class Thema{
	
	/**
	 * der Standardkonstruktor
	 */
	public Thema() {};
	
	/**
	 * der parametrisierte Konstruktor der Klasse Thema
	 * @param id die ID des Schwierigkeitsgrad
	 * @param text der Name des Schwierigkeitsgrads
	 */
	public Thema(Integer id, String text) {
		this.id = id;
		this.text = text;
	};
	
	/**
	 * die Id des Themengebiets
	 */
	private Integer id;
	
	/**
	 * der Name des Themengebiets
	 */
	private String text;
	
	/**
	 * gibt die Id des Themengebiets zurueck
	 * @return die Id des Themengebiets
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * setzt die Id des Themengebiets
	 * @param id die Id des Themengebiets
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * gibt den Namen des Themengebiets zurück
	 * @return der Name des Themengebiets
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * setzt den Namen des Themengebiets
	 * @param text der Name des Themengebiets
	 */
	public void setText(String text) {
		this.text = text;
	}
}
