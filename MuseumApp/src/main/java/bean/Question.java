package bean;

public class Question {

	private Integer id;
	private String text;
	private Integer difficulty;
	private Integer topic;
	
	public Question() {}
	
	public Question(Integer id, String text, Integer difficulty, Integer topic) {
		this.id = id;
		this.text = text;
		this.difficulty = difficulty;
		this.topic = topic;
	}
	
	public Integer getTopic() {
		return topic;
	}
	public void setTopic(Integer topic) {
		this.topic = topic;
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

	public Integer getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}

	
}
