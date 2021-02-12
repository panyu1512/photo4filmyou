package jpa;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="brand")
public class BrandJPA implements Serializable{

	private static final long serialVersionUID = 1L;

    @Id
	@Column(name="id")
	@SequenceGenerator(name = "id_brand_seq", sequenceName = "id_brand_seq", allocationSize = 1, initialValue=5)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_brand_seq")
	private Integer id;
	
	@Column(unique=true, nullable = false)
	private String name;	
	

	public BrandJPA() {
		super();
	}

	public BrandJPA(String name) {
		super();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
