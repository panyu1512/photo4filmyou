package jpa;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import domain.Email;
import domain.Password;
import domain.Roles;

/**
 * JPA Class Administrator
 */
@Entity
@Table(name="administrator")
public class AdministratorJPA extends UserJPA {

 
	private static final long serialVersionUID = 3433061748450888104L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    
    @Column
    private Date date;

    public AdministratorJPA()
    {
        super();
    }

    public AdministratorJPA(Password password, Email email)
    {
        super(email, password);
    }
    
    public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

    @Override
    public Roles getRole()
    {
        return Roles.Administrator;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
    
}
