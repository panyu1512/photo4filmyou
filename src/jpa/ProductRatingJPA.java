package jpa;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="product_rating")
public class ProductRatingJPA implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
	@SequenceGenerator(name = "product_rating_id_seq", sequenceName = "product_rating_id_seq", allocationSize = 1, initialValue=6)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_rating_id_seq")
    private Integer id;

    @Column
    private String comment;

    @Column
    private int rating;
    
    @ManyToOne
	@JoinColumn(name = "product", nullable = false)
	private ProductJPA product;
    
    @ManyToOne
	@JoinColumn(name = "customer", nullable = false)
	private CustomerJPA customer;

    public ProductRatingJPA() {

    }

    public ProductRatingJPA(String comment, int rating, ProductJPA product, CustomerJPA customer) {
    	this.comment = comment;
    	this.rating = rating;
    	this.product = product;
    	this.customer = customer;
    }

    public int getIdProductRating() {
        return id;
    }

    public void setIdProductRating(Integer idProductRating) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
