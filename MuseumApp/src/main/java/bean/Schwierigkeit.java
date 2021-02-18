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
	 * der parametrisierte Konstruktor der Klasse Difficulty
	 * @param id die ID der Schwierigkeitsstufe
	 * @param text der Name der Schwierigkeitsstufe
	 */
	public Schwierigkeit(Integer id, String text) {
		this.id = id;
		this.text = text;
	};
	
	private Integer id;
	private String text;
	
	/**
	 * gibt die ID der Schwierigkeitsstufe zurueck
	 * @return die ID der Schwierigkeitsstufe
	 */
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
