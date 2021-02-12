package jpa;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import auth.UserLoggableInterface;
import domain.Email;
import domain.Password;
import domain.converter.EmailConverter;
import domain.converter.PasswordConverter;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
abstract public class UserJPA implements UserLoggableInterface{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
	
	@Column(unique=true)
    @Convert(converter = EmailConverter.class)
    protected Email email;

    @Column
    @Convert(converter = PasswordConverter.class)
    protected Password password;

    protected UserJPA()
    {

    }

    protected UserJPA(Email email, Password password) {
        this.email = email;
        this.password = password;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public Email getEmail() {
        return this.email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
    
    
}
