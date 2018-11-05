package services;

import java.sql.Blob;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Quiz;
import repository.QuizRep;

@Service
public class QuizServices {

	@Autowired
	private QuizRep quizRep;

	public List<Quiz> getAllQuiz() {
		try {
			return quizRep.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public void addQuiz(Quiz q) {
		quizRep.save(q);
	}

	public Quiz findQuiz(Long id) {
		return quizRep.findQuizById(id);
	}

	public void updateQuiz(Quiz q) {
		quizRep.save(q);
	}

	public void updateQuizInfo(String name, String title, Long quizId, byte[] bytes) {

		try {
			Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
			quizRep.updateQuizInfo(name, title, quizId, blob);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void deleteQuiz(Quiz q) {
		quizRep.delete(q);
	}

	public QuizRep getQuizRep() {
		return quizRep;
	}

	public void setQuizRep(QuizRep quizRep) {
		this.quizRep = quizRep;
	}

}
