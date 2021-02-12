package jpa;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

/**
 * JPA Class CategoryJPA
 */
@Entity
@Table(name="category")
@NamedQueries({
		@NamedQuery(name = CategoryJPA.FIND_BY_NAME, query = "SELECT c FROM CategoryJPA c where c.name = :name")})
public class CategoryJPA extends CatalogElement implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
	@Column(name="id")
	@SequenceGenerator(name = "id_category_seq", sequenceName = "id_category_seq", allocationSize = 1, initialValue=7)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_category_seq")
	private Integer id;
    
	@Column(unique=true, nullable = false)
	private String name;
	
    
	@ManyToOne
	@JoinColumn (name="parent")
	private CategoryJPA parent;
	
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER )
	@JoinColumn(name = "parent") 
	private Collection<CategoryJPA> categories; 
	

	//private Collection<CategoryJPA> categories; 

	public static final String FIND_BY_NAME = "category.findByName";

	/**
	 * Class constructor methods
	 */
	public CategoryJPA() {
		super();
	}
	public CategoryJPA(String name, CategoryJPA category) {
		this.name = name;
		this.parent = category;
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
	
	/**
	 * Methods get/set persistent relationships
	 */

	public CategoryJPA getParent() {
		return parent;
	}
	public  void setParent(CategoryJPA parent) {
		this.parent = parent;
	}


}
