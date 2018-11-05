package repository;

import java.sql.Blob;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import model.Quiz;

@Repository
public interface QuizRep extends JpaRepository<Quiz, Long> {

	public Quiz findQuizById(Long id);
	
	@Modifying
	@Transactional
	@Query("update Quiz q set q.name = :name , q.title = :title, q.image = :image where q.id = :quizId")
	void updateQuizInfo(@Param("name") String name, @Param("title") String title, @Param("quizId") Long quizId,
			@Param("image") Blob image);
	
}
