package bean;

public class Answer {
	
	private Integer id;
	private String text;
	private Integer id_question;
	private boolean isCorrect;
	
	public Answer() {};
	
	public Answer(Integer id, String text, Integer id_question, boolean isCorrect) {
		this.id = id;
		this.text = text;
		this.id_question = id_question;
		this.isCorrect = isCorrect;
	};
	
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
	
	public Integer getId_frage() {
		return id_question;
	}
	public void setId_frage(Integer id_question) {
		this.id_question = id_question;
	}
	
	public boolean getIsCorrect() {
		return isCorrect;
	}
	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

}
