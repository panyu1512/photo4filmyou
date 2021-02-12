package jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import domain.MoneyV2;
import domain.PenalizationStatus;
import domain.converter.MoneyConverterV2;

@Entity
@Table(name="cancellation")
public class CancellationJPA  implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "creationDate", updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(name="penalization")
    @Convert(converter = MoneyConverterV2.class)
    private MoneyV2 penalization;

    @Column
    @Enumerated(value = EnumType.STRING)
    private PenalizationStatus penalizationStatus;


    @OneToOne(mappedBy = "cancellation", cascade = CascadeType.ALL)
    private RentJPA rentJPA;



    public Integer getCancellationId() {
        return id;
    }

    public void setCancellationId(Integer cancellationId) {
        this.id = cancellationId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public MoneyV2 getPenalization() {
        return penalization;
    }

    public void setPenalization(MoneyV2 penalization) {
        this.penalization = penalization;
    }

    public PenalizationStatus getPenalizationStatus() {
        return penalizationStatus;
    }

    public void setPenalizationStatus(PenalizationStatus penalizationStatus) {
        this.penalizationStatus = penalizationStatus;
    }

    public RentJPA getRentJPA() {
        return rentJPA;
    }

    public void setRentJPA(RentJPA rentJPA) {
        this.rentJPA = rentJPA;
    }


}
