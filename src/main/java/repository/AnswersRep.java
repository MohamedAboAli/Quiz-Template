package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Answers;
import model.Questions;

@Repository
public interface AnswersRep extends JpaRepository<Answers, Long> {

	public Answers findAnswersById(Long id);
	
	public List<Answers> findAnswersByQuestionID(Long id);
	
}
