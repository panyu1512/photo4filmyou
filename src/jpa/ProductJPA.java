package jpa;
import java.io.Serializable;
import java.util.Collection;

import javax.persistence.*;

import domain.MoneyV2;
import domain.converter.MoneyConverterV2;


@Entity
@Table(name = "product")
public class ProductJPA extends CatalogElement implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id
	@Column(name="id")
	@SequenceGenerator(name = "id_product_seq", sequenceName = "id_product_seq", allocationSize = 1, initialValue=10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_product_seq")
	private Integer id;


	@ManyToOne
	@JoinColumn(name = "category", nullable = false)
	private CategoryJPA category;
	
	
	@ManyToOne
	@JoinColumn(name = "brand", nullable = false)
	private BrandJPA brand;
	
	@ManyToOne
	@JoinColumn(name = "model", nullable = false)
	private ModelJPA model;

	@Column(name="dailyPrice")
	@Convert(converter = MoneyConverterV2.class)
	private MoneyV2 dailyPrice;

	@Column(name="description")
	private String description;

	/**
	 * Methods get/set persistent relationships
	 */
	@OneToMany(mappedBy = "product")
	private Collection<ProductRatingJPA> ratings;

	/**
	 * Methods get/set persistent relationships
	 */
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Collection<ItemJPA> items;

	@Transient
	private int availableItems;

	@Transient
	private float productRating;

	@PostLoad
	private void onLoad() {
		//Calcular el productRating
		int tempRating = 0;
		for (ProductRatingJPA pr : ratings) {
	        tempRating += pr.getRating();
	    }
		try {
			productRating = tempRating / ratings.size();
		} catch(Exception e) {
			productRating = 0;
		}
		//Calcular availableItems
		availableItems = items.size();

	}

	/**
	 * Class constructor methods
	 */
	public ProductJPA() {
		super();
	}

	public ProductJPA(CategoryJPA category, String name, BrandJPA brand, ModelJPA model, MoneyV2 dailyPrice, String description) {
		super(name);
		this.category=category;
		this.brand=brand;
		this.model=model;
		this.dailyPrice=dailyPrice;
		this.description=description;
	}

	public Integer getId() {
		return id;
	}

	public CategoryJPA getCategory() {
		return category;
	}
	public void setCategory(CategoryJPA category) {
		this.category = category;
	}
	public BrandJPA getBrand() {
		return brand;
	}
	public void setBrand(BrandJPA brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public MoneyV2 getDailyPrice() {
		return dailyPrice;
	}
	public void setDailyPrice(MoneyV2 dailyPrice) {
		this.dailyPrice = dailyPrice;
	}
	public ModelJPA getModel() {
		return model;
	}
	public void setModel(ModelJPA model) {
		this.model = model;
	}
	public Integer getIdProduct() {
		return id;
	}
	public void setId(Integer idProduct) {
		this.id = idProduct;
	}

	public Collection<ProductRatingJPA> getRatings() {
		return ratings;
	}

	public void setRatings(Collection<ProductRatingJPA> ratings) {
		this.ratings = ratings;
	}

	public void setIdProduct(Integer idProduct) {
		this.id = idProduct;
	}

	public Collection<ItemJPA> getItems() {
		return items;
	}

	public void setItems(Collection<ItemJPA> items) {
		this.items = items;
	}


	public int getAvailableItems() {
		return availableItems;
	}

	public void setAvailableItems(int availableItems) {
		this.availableItems = availableItems;
	}

	public float getProductRating() {
		return productRating;
	}

	public void setProductRating(float productRating) {
		this.productRating = productRating;
	}


}
