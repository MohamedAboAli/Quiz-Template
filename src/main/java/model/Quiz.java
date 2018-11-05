package model;

import java.sql.Blob;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "quiz", schema = "CBCTask")
public class Quiz {

	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private Long id;
		
	    @Column(name = "image")
	    @JsonIgnore
		private Blob image;
	    
	    @Column(name = "name")
		private String name;
	    
	    @Column(name = "title")
		private String title;

	    
	    @OneToMany(mappedBy="quizID", fetch= FetchType.EAGER)
	    private List<Questions> questions;
	    
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Blob getImage() {
			return image;
		}

		public void setImage(Blob image) {
			this.image = image;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public List<Questions> getQuestions() {
			return questions;
		}

		public void setQuestions(List<Questions> questions) {
			this.questions = questions;
		}

	    
	
}
