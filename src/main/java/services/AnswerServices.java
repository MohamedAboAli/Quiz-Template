package services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Answers;
import model.Questions;
import repository.AnswersRep;

@Service
public class AnswerServices {

	@Autowired
	private AnswersRep answersRep;

	public void saveAnswers(Answers a) {
		answersRep.save(a);
	}

	public void deleteAnswer(Long answerId) {

		Answers a = answersRep.findAnswersById(answerId);
		answersRep.delete(a);
	}

	public List<Answers> getAllAnswersByQuizId(Long questionId) {

		return answersRep.findAnswersByQuestionID(questionId);
	}

	public AnswersRep getAnswersRep() {
		return answersRep;
	}

	public void setAnswersRep(AnswersRep answersRep) {
		this.answersRep = answersRep;
	}

}
