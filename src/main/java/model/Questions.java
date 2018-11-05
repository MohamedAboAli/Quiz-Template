package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Questions", schema = "CBCTask")
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "question")
	private String question;

	@Column(name = "asnwer_type")
	private int asnwer_type;
	
	@Column(name = "quizID")
	private Long quizID;

    @OneToMany(mappedBy="questionID", fetch= FetchType.EAGER)
    private List<Answers> answers;
	
	public List<Answers> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answers> answers) {
		this.answers = answers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getAsnwer_type() {
		return asnwer_type;
	}

	public void setAsnwer_type(int asnwer_type) {
		this.asnwer_type = asnwer_type;
	}

	public Long getQuizID() {
		return quizID;
	}

	public void setQuizID(Long quizID) {
		this.quizID = quizID;
	}


}
