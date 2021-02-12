package jpa;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import domain.ItemStatus;

@Entity
@Table(name="item")
public class ItemJPA implements Serializable{

	private static final long serialVersionUID = 1L;

	//TODO: create Value Object
    @Id
	@Column(name="serialNumber")
	private String serialNumber;


	@Column
	@Enumerated(value = EnumType.STRING)
	private ItemStatus status;

	public ProductJPA getProduct() {
		return product;
	}

	public void setProduct(ProductJPA product) {
		this.product = product;
	}

	@ManyToOne
	@JoinColumn(name = "idProduct", nullable = false)
	private ProductJPA product;


	/**
	 * Class constructor methods
	 */
	public ItemJPA() {
		super();
	}

	public ItemJPA(ProductJPA product, String serialNumber, ItemStatus status) {
		this.product = product;
		this.serialNumber = serialNumber;
		this.status=status;
	}


	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public ItemStatus getStatus() {
		return status;
	}
	public void setStatus(ItemStatus status) {
		this.status = status;
	}


}
