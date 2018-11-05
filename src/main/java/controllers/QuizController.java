package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import model.Quiz;
import services.QuizServices;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizServices quizService;

	@RequestMapping(method = RequestMethod.GET, value = "/getAllQuizs")
	public List<Quiz> getAllQuizs() {
		try {
			return quizService.getAllQuiz();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	// saveQuiz is web service that add quiz in database
	// note : image must be blob to add that in database
	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public String saveQuiz(@RequestBody Quiz q) {
		try {
			quizService.addQuiz(q);
			return "Quiz Added Successfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "unSuccessfully";
		}

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
	public String updateQuiz(@RequestBody Quiz quiz, @PathVariable("id") Long id) {
		try {
			Quiz q = quizService.findQuiz(id);
			q.setName(quiz.getName());
			q.setTitle(quiz.getTitle());
			if (quiz.getImage() != null) {
				q.setImage(quiz.getImage());
			}
			quizService.updateQuiz(q);
			return "Quiz Updated Successfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "unSuccessfully";
		}

	}

	// other way to update Quiz info.
	// receive Image as MultiPart
	@RequestMapping(value = "/updateQuizInfo", method = RequestMethod.PUT)
	public ResponseEntity<?> updateQuizInfo(@RequestParam("name") String name, @RequestParam("title") String title,
			@RequestParam("quizId") Long quizId, @RequestParam("image") MultipartFile image) {
		try {
			/*
			 * that for test web service i tried to send multipart from postman
			 * and i can't do that so i get file from my local desktop
			 * 
			 * File file = new File("C:/Users/Mohammed Abo Ali/Desktop/v.png");
			 * byte[] fileContent = Files.readAllBytes(file.toPath());
			 */

			quizService.updateQuizInfo(name, title, quizId, image.getBytes());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/deleteQuiz", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteQuiz(@RequestParam("quizId") Long Quizid) {
		try {
			Quiz q = quizService.findQuiz(Quizid);
			quizService.deleteQuiz(q);
			return new ResponseEntity<String>("Deleted Sucessfully", HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>("unSuccessfully", HttpStatus.BAD_REQUEST);
		}

	}

	public QuizServices getQuizService() {
		return quizService;
	}

	public void setQuizService(QuizServices quizService) {
		this.quizService = quizService;
	}

}
