package jpa;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity
@Table(name="model")
@NamedQueries({
	@NamedQuery(name = ModelJPA.FIND_ALL, query = "SELECT g FROM ModelJPA g order by g.id desc ")})
public class ModelJPA implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String FIND_ALL = "ModelJPA.findAll";

	
    @Id
	@Column(name="id")
	@SequenceGenerator(name = "id_model_seq", sequenceName = "id_model_seq", allocationSize = 1, initialValue=5)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_model_seq")
	private Integer id;
    
   
    @Column
	private String name;

	
	/**
	 * Class constructor methods
	 */
	public ModelJPA() {
		super();
	}	
	public ModelJPA(String name) {				
		this.name = name;
	}

	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

	

	
}
