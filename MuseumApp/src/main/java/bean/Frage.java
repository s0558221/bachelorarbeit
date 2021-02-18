package bean;

public class Frage {

	private Integer id;
	private String text;
	private Integer schwierigkeit;
	private Integer thema;
	
	public Frage() {}
	
	public Frage(Integer id, String text, Integer schwierigkeit, Integer thema) {
		this.id = id;
		this.text = text;
		this.schwierigkeit = schwierigkeit;
		this.thema = thema;
	}
	
	public Integer getThema() {
		return thema;
	}
	public void setThema(Integer thema) {
		this.thema = thema;
	}

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

	public Integer getSchwierigkeit() {
		return schwierigkeit;
	}

	public void setSchwierigkeit(Integer schwierigkeit) {
		this.schwierigkeit = schwierigkeit;
	}

	
}
