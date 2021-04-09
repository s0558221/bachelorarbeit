package bean;

/**
 * repraesentiert die Schwiergkeitsstufe der einzelnen Quizfragen
 * @author Roy Beyer
 *
 */
public class Schwierigkeit{
	
	/**
	 * der parameterlose Standardkonstruktor
	 */
	public Schwierigkeit() {};
	
	/**
	 * der parametrisierte Konstruktor der Klasse Schwierigkeit
	 * @param id die ID des Schwierigkeitsgrad
	 * @param text der Name des Schwierigkeitsgrads
	 */
	public Schwierigkeit(Integer id, String text) {
		this.id = id;
		this.text = text;
	};
	
	/**
	 * die Id des Schwierigkeitsgrads
	 */
	private Integer id;
	
	/**
	 * 	der Name des Schwierigkeitsgrads
	 */
	private String text;
	
	/**
	 * gibt die ID des Schwierigkeitsgrads zurueck
	 * @return die ID des Schwierigkeitsgrads
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * setzt die Id des Schwierigkeitsgrads
	 * @param id die Id des Schwierigkeitsgrads
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * gibt den Namen des Schwierigkeitsgrads zurueck
	 * @return der Name des Schwierigkeitsgrads
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * setzt den Namen des Schwierigkeitsgrads
	 * @param text derName des Schwierigkeitsgrads
	 */
	public void setText(String text) {
		this.text = text;
	}
}
