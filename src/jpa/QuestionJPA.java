package jpa;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="question")
@NamedQuery(name="Question.findById", query = "select q from QuestionJPA q where id = :id")
public class QuestionJPA implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
	
    @Column
    private String title;
    
    @Column
    private String message;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_response", updatable = true, nullable = true)
    private ResponseJPA response;
    
    @ManyToOne
    @JoinColumn(name = "fk_askedBy", updatable = true, nullable = true)
    private CustomerJPA askedBy;

	public QuestionJPA() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QuestionJPA(String title, String message, CustomerJPA askedBy) {
		super();
		this.title = title;
		this.message = message;
		this.askedBy = askedBy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

	public ResponseJPA getResponse() {
		return response;
	}

	public void setResponse(ResponseJPA response) {
		this.response = response;
	}

	public CustomerJPA getAskedBy() {
		return askedBy;
	}

	public void setAskedBy(CustomerJPA askedBy) {
		this.askedBy = askedBy;
	}

    
}
