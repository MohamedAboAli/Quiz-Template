package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Questions;
import model.Quiz;
import services.QuestionServices;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionServices questionServices;
	
	@RequestMapping(method = RequestMethod.GET, value = "/getAllQuestionByQuizId")
	public List<Questions> getAllQuestionByQuizId(@RequestParam("quizId") Long quizId) {
		try {
			return questionServices.getAllQuestionByQuizId(quizId);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getQuestionById")	
	public Questions getQuestionById(@RequestParam("questionId") Long questionId)
	{
		try {
			return questionServices.findQuesById(questionId);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public String saveQuiz(@RequestBody Questions q) {
		try {	
			questionServices.addQuestion(q);
			return "Question Added Successfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "unSuccessfully";
		}

	}
	
	@RequestMapping(value = "/deleteQuestion", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteQuestion(@RequestParam("questionId") Long questionId) {
		try {
			
			questionServices.deleteQuestion(questionId);
			return new ResponseEntity<String>("Deleted Sucessfully", HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>("unSuccessfully", HttpStatus.BAD_REQUEST);
		}

	}

	public QuestionServices getQuestionServices() {
		return questionServices;
	}

	public void setQuestionServices(QuestionServices questionServices) {
		this.questionServices = questionServices;
	}
	
	
	}
	
	
	

