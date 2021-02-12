package jpa;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import domain.MoneyV2;
import domain.RentStatus;

@Entity
@Table(name="rent")
public class  RentJPA  implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "fromDate", updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date from;

    @Column(name = "toDate", updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date to;

    @Column
    @Enumerated(value = EnumType.STRING)
    private RentStatus status;


    @OneToOne(targetEntity = CancellationJPA.class)
    @JoinColumn(name = "cancellationId", referencedColumnName = "id")
    private CancellationJPA cancellation;

    @ManyToOne
    @JoinColumn(name = "customerId", nullable = false, referencedColumnName = "id")
    private CustomerJPA customerJPA;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = ItemJPA.class)
    private Set<ItemJPA> items;

    @Transient
    private MoneyV2 totalPrice;

    @Transient
    private int rentDays;

    @PostLoad
    private void onLoad(){
        if(this.from != null && this.to != null) {
            this.rentDays = (int) ChronoUnit.DAYS.between(Instant.ofEpochMilli(this.from.getTime()), Instant.ofEpochMilli(this.to.getTime()));
        }
        this.totalPrice = new MoneyV2(this.getTotalPriceComputed());
    }

    private BigDecimal getTotalPriceComputed() {
        Iterator<ItemJPA> it = this.items.iterator();
        BigDecimal localTotalPrice = new BigDecimal(0.0);
        while(it.hasNext()) {
            ItemJPA itemJPA  = it.next();
            localTotalPrice = localTotalPrice.add(itemJPA.getProduct().getDailyPrice().getAmount());
        }
        return localTotalPrice;
    }

    public RentJPA() {
    	super();
    }

    public RentJPA(Date from, Date to, MoneyV2 totalPrice, Integer rentDays, RentStatus status) {
        this.from = from;
        this.to = to;
        this.totalPrice = totalPrice;
        this.rentDays = rentDays;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public CustomerJPA getCustomerJPA() {
        return customerJPA;
    }

    public void setCustomerJPA(CustomerJPA customerJPA) {
        this.customerJPA = customerJPA;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public MoneyV2 getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(MoneyV2 totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getRentDays() {
        return rentDays;
    }

    public void setRentDays(Integer rentDays) {
        this.rentDays = rentDays;
    }

    public RentStatus getStatus() {
        return status;
    }

    public void setStatus(RentStatus status) {
        this.status = status;
    }


    public CancellationJPA getCancellation() {
        return cancellation;
    }

    public void setCancellation(CancellationJPA cancellation) {
        this.cancellation = cancellation;
    }

    public Set<ItemJPA> getItems() {
        return items;
    }

    public void setItems(Set<ItemJPA> items) {
        this.items = items;
    }

    public void setRentDays(int rentDays) {
        this.rentDays = rentDays;
    }
}
