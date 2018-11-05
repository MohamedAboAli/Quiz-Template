package controllers;

import java.sql.Blob;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import model.Answers;
import model.Questions;
import services.AnswerServices;

@RestController
@RequestMapping("/answer")
public class AnswersController {

	@Autowired
	private AnswerServices answerServices;

	// Outcomes Builder : Write description or upload image
	// i put type here to determine type of answer
	// correct_answer id attribute if user set it for 1 it become correct answer for question
	// if type = 1 it is Outcomes Builder if it = 0 it is URL Redirect
	@RequestMapping(method = RequestMethod.POST, value = "/saveAnswers")
	public String saveQuiz(@RequestParam("redirect_url") String redirect_url,
			@RequestParam("description") String description, @RequestParam("title") String title,
			@RequestParam("questionID") Long questionID, @RequestParam("correct_answer") int correct_answer,
			@RequestParam("type") int type, @RequestBody MultipartFile image) {
		try {
			Answers a = new Answers();
			a.setCorrect_answer(correct_answer);
			a.setTitle(title);
			a.setType(type);
			a.setQuestionID(questionID);
			if (type == 1) {
				/*
				 * Outcomes Builder answers Write description or upload image
				 */
				if (description != null) {
					a.setDescription(description);
				}
				if (image != null) {
					Blob blob = new javax.sql.rowset.serial.SerialBlob(image.getBytes());
					a.setImage(blob);
				}
				if (type == 0) {
					/*
					 * • URL Redirect answers
					 */
					a.setRedirect_url(redirect_url);
				}

			}

			answerServices.saveAnswers(a);
			return "Answer Added Successfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			return "unSuccessfully";
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getAllAnswersByQuestionId")
	public List<Answers> getAllQuestionByQuizId(@RequestParam("questionId") Long questionId) {
		try {
			return answerServices.getAllAnswersByQuizId(questionId);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@RequestMapping(value = "/deleteAnswer", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteQuestion(@RequestParam("answerId") Long answerId) {
		try {

			answerServices.deleteAnswer(answerId);
			return new ResponseEntity<String>("Deleted Sucessfully", HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<String>("unSuccessfully", HttpStatus.BAD_REQUEST);
		}

	}

	public AnswerServices getAnswerServices() {
		return answerServices;
	}

	public void setAnswerServices(AnswerServices answerServices) {
		this.answerServices = answerServices;
	}

}
