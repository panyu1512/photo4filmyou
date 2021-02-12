package jpa;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import domain.Email;
import domain.Location;
import domain.NIF;
import domain.Password;
import domain.PhoneNumber;
import domain.Roles;
import domain.converter.LocationConverter;
import domain.converter.NIFConverter;
import domain.converter.PhoneConverter;


@Entity
@Table(name="customer")
public class CustomerJPA  extends UserJPA  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    
    @Column
    @Convert(converter = NIFConverter.class)
    private NIF nif;
    
    @Column
    @Convert(converter = LocationConverter.class)
    private Location address;
    
    @Column
    @Convert(converter = PhoneConverter.class)
    private PhoneNumber phone;

	/**
	 * Methods get/set persistent relationships
	 */
	@OneToMany(targetEntity = ProductRatingJPA.class)
	private Collection<ProductRatingJPA> ratings;

    @Column
    private String name;
    
    @Column
    private String surname;
    
    public CustomerJPA() {
		super();
	}
    
    public CustomerJPA(NIF nif, String name, String surname, PhoneNumber phone, Location address, Password password, Email email) {
		super(email, password);
		this.nif = nif;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.address = address;
	}

	public int getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}

	public Collection<ProductRatingJPA> getRatings() {
		return ratings;
	}

	public void setRatings(Collection<ProductRatingJPA> ratings) {
		this.ratings = ratings;
	}

	public NIF getNif() {
		return nif;
	}

	public void setNif(NIF nif) {
		this.nif = nif;
	}

	public Location getAddress() {
		return address;
	}

	public void setAddress(Location address) {
		this.address = address;
	}

	public PhoneNumber getPhone() {
		return phone;
	}

	public void setPhone(PhoneNumber phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public Roles getRole() {
		return Roles.Customer;
	}

	
    
    

}
