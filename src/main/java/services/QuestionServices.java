package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Questions;
import repository.QuestionRep;

@Service
public class QuestionServices {

	@Autowired
	private QuestionRep questionRep;

	public void addQuestion(Questions question) {
	
			questionRep.save(question);
		
	}

	public void deleteQuestion(Long questionId) {
		
			Questions q = questionRep.findQuestionsById(questionId);
			questionRep.delete(q);
		

	}

	public Questions findQuesById(Long id) {
	
			return questionRep.findQuestionsById(id);
		
	}

	public List<Questions> getAllQuestionByQuizId(Long quizId) {
		
			return questionRep.findQuestionsByQuizID(quizId);
	}

	public QuestionRep getQuestionRep() {
		return questionRep;
	}

	public void setQuestionRep(QuestionRep questionRep) {
		this.questionRep = questionRep;
	}
	
	
}
