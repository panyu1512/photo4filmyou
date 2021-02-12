package jpa;

import java.io.Serializable;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import domain.ResponseStatus;

@Entity
@Table(name = "response")
@NamedQuery(name = "Response.findByQuestionId", query = "select r from ResponseJPA r, QuestionJPA q where q.id = :questionId and q.response.id = r.id")
public class ResponseJPA implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResponseJPA() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResponseJPA(String message, ResponseStatus status, UserJPA answeredBy) {
		this.message = message;
		this.status = status;
		this.answeredBy = answeredBy;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@OneToOne(mappedBy = "response", cascade = CascadeType.ALL)
	private QuestionJPA question;
	
    @ManyToOne
    @JoinColumn(name = "fk_answeredBy", updatable = true, nullable = true)
    private UserJPA answeredBy;
	
	@Column
	private String message;

	@Enumerated(EnumType.STRING)
	@Column
	private ResponseStatus status;

	@Basic(fetch = FetchType.LAZY)
	private byte[] attachedImage;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public QuestionJPA getQuestion() {
		return question;
	}

	public void setQuestion(QuestionJPA question) {
		this.question = question;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public byte[] getAttachedImage() {
		return attachedImage;
	}

	public void setAttachedImage(byte[] image) {
		this.attachedImage = image;
	}

	public UserJPA getAnsweredBy() {
		return answeredBy;
	}

	public void setAnsweredBy(UserJPA answeredBy) {
		this.answeredBy = answeredBy;
	}
	
	public String getAttachedImageAsBase64() {
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(attachedImage);
	}
}
