package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Questions;

@Repository
public interface QuestionRep extends JpaRepository<Questions, Long> {

	public Questions findQuestionsById(Long id);
	
	public List<Questions> findQuestionsByQuizID(Long id);
	
	
	
}
