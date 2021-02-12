package ejb.rent;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import auth.util.SessionManager;
import domain.ItemStatus;
import domain.MoneyV2;
import domain.PenalizationStatus;
import domain.RentStatus;
import ejb.profile.ProfileFacadeBean;
import jpa.BrandJPA;
import jpa.CancellationJPA;
import jpa.CustomerJPA;
import jpa.ItemJPA;
import jpa.ModelJPA;
import jpa.ProductJPA;
import jpa.RentJPA;

@Stateless
public class RentFacadeBean<T> implements RentFacade<T>, RentFacadeRemote<T> {

    Logger logger = Logger.getLogger(ProfileFacadeBean.class.getName());

    //Persistence Unit Context
    @PersistenceContext(unitName = "PDS2_PhotoFilm4You")
    private EntityManager entman;


    @Override
    public Collection <ProductJPA> listProductsWithAvailableItems(Date from, Date to, Integer idBrand, Integer idModel, String description){

        BrandJPA brandJpa;
        ModelJPA modelJPA;
        try {
             brandJpa = this.entman.find(BrandJPA.class, idBrand);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        try {
             modelJPA = this.entman.find(ModelJPA.class, idModel);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if(from != null && to != null) {
            return this.listProductsWithDateFiler(from, to, brandJpa.getId(), modelJPA.getId(), description);
        }
        else {
            return this.listProductsWithoutDateFilter(brandJpa.getId(), modelJPA.getId(), description);
        }
    }

    private Collection<ProductJPA> listProductsWithoutDateFilter(Integer idBrand, Integer idModel, String description) {

        return this.entman.createNativeQuery("SELECT  DISTINCT p.* from pds2_photofilm4you.product p" +
                "    INNER JOIN pds2_photofilm4you.item it ON (it.idproduct = p.id)" +
                "    LEFT  JOIN  pds2_photofilm4you.rent_item rt ON(rt.items_serialnumber=it.serialnumber)" +
                "    LEFT  JOIN  pds2_photofilm4you.rent r ON(rt.rentjpa_id=r.id)" +
                "    WHERE p.brand = :idBrand AND" +
                "          p.model = :idModel AND" +
                "          p.description LIKE :textDescription AND"+
                "          it.status = :statusItem AND" +
                "          (rt.rentjpa_id IS NULL OR" +
                "          (rt.rentjpa_id IS NOT NULL  AND  NOT (r.todate BETWEEN now() AND now() + INTERVAL  '1y') or r.status = :statusRent))"
                , ProductJPA.class)
                .setParameter("idBrand", idBrand.intValue())
                .setParameter("idModel", idModel.intValue())
                .setParameter("textDescription", "%" + description.toLowerCase() + "%")
                .setParameter("statusItem", ItemStatus.Operational.toString())
                .setParameter("statusRent", RentStatus.Cancelled.toString())
                .getResultList();

    }

    private Collection<ProductJPA> listProductsWithDateFiler(Date from, Date to, Integer idBrand, Integer idModel, String description) {
        return this.entman.createNativeQuery("SELECT  DISTINCT p.* from pds2_photofilm4you.product p" +
                        "    INNER JOIN pds2_photofilm4you.item it ON (it.idproduct = p.id)" +
                        "    LEFT  JOIN  pds2_photofilm4you.rent_item rt ON(rt.items_serialnumber=it.serialnumber)" +
                        "    LEFT  JOIN  pds2_photofilm4you.rent r ON(rt.rentjpa_id=r.id)" +
                        "    WHERE p.brand = :idBrand AND" +
                        "          p.model = :idModel AND" +
                        "          p.description  LIKE :textDescription AND" +
                        "          it.status = :statusItem AND" +
                        "          (rt.rentjpa_id IS NULL OR" +
                        "          (rt.rentjpa_id IS NOT NULL AND  r.id NOT in (select f2.id from pds2_photofilm4you.rent f2 " +
                        "                                                       where (f2.fromdate BETWEEN :dateStart AND  :dateEnd " +
                        "                                                          or f2.todate BETWEEN :dateStart AND :dateEnd) AND f2.status != :statusRent)))"
                , ProductJPA.class)
                .setParameter("idBrand", idBrand.intValue())
                .setParameter("idModel", idModel.intValue())
                .setParameter("textDescription", "%" + description.toLowerCase() + "%")
                .setParameter("dateStart",  from, TemporalType.DATE)
                .setParameter("dateEnd",    to, TemporalType.DATE)
                .setParameter("statusItem", ItemStatus.Operational.toString())
                .setParameter("statusRent", RentStatus.Cancelled.toString())
                .getResultList();
    }
    

    @Override
    public Collection<ItemJPA> listAllRentItems(Integer rentId) {
        RentJPA rentJPAEntity = this.getRent(rentId);
        return rentJPAEntity.getItems();
    }

    public void addItemToRent(Integer rentId, String serialNumber) {
        RentJPA rentJPAEntity = this.getRent(rentId);
        ItemJPA itemJPAEntity = this.entman.find(ItemJPA.class, serialNumber);
        rentJPAEntity.getItems().add(itemJPAEntity);
        this.entman.persist(rentJPAEntity);
    }

    @Override
    public RentJPA getRent(Integer rentId) {
        return this.entman.find(RentJPA.class, rentId);
    }

    @Override
    public void paymentDone(Integer rentId) {
        RentJPA rentJPAEntity = this.getRent(rentId);
        CancellationJPA cancellationJPA = new CancellationJPA();
        cancellationJPA.setPenalization(new MoneyV2( new BigDecimal(0)));
        cancellationJPA.setPenalizationStatus(PenalizationStatus.Paid);
        cancellationJPA.setCreationDate(new Date());
        try {
            this.updateRentStatus(rentId, RentStatus.Confirmed);
            this.entman.persist(cancellationJPA);
            rentJPAEntity.setCancellation(cancellationJPA);
            this.entman.persist(rentJPAEntity);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createRent(Date from, Date to) {
        RentJPA rentJPAEntity = new RentJPA();
        rentJPAEntity.setFrom(from);
        rentJPAEntity.setTo(to);
        rentJPAEntity.setStatus(RentStatus.NotConfirmed);

        CustomerJPA customer = (CustomerJPA) SessionManager.getUser();
        rentJPAEntity.setCustomerJPA(customer);

        try {
            this.entman.persist(rentJPAEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void confirmRent(Integer rentId) {
        RentJPA rentJPAEntity = this.getRent(rentId);
        rentJPAEntity.setStatus(RentStatus.Confirmed);
        this.entman.persist(rentJPAEntity);
    }

    @Override
    public void updateRentStatus(Integer rentId, RentStatus status) {
        RentJPA rentJPAEntity = this.getRent(rentId);
        rentJPAEntity.setStatus(status);
        try {
            this.entman.persist(rentJPAEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void cancelRent(Integer rentId) {
        RentJPA rentJPAEntity = this.getRent(rentId);
        
        if (rentJPAEntity.getStatus() != RentStatus.Cancelled) 
	    {
	        rentJPAEntity.setStatus(RentStatus.Cancelled);
	        
	        CancellationJPA cancellationEntity = new CancellationJPA();
	        
	        BigDecimal importe; 
	        Date currentDate = new Date();
	        PenalizationStatus penalizationStatus;
	        
	        if (rentJPAEntity.getFrom().after((addHoursDate(currentDate,48)))
	        || (rentJPAEntity.getFrom().equals(addHoursDate(currentDate,48))))
	        {
	        	penalizationStatus = PenalizationStatus.Paid;
	        	importe = new BigDecimal(0.00);       	
	        }
	        else if (rentJPAEntity.getFrom().after(addHoursDate(currentDate,24))
	        	|| (rentJPAEntity.getFrom().equals(addHoursDate(currentDate,24))))
	        {
	        	penalizationStatus = PenalizationStatus.Pending;
	        	importe = new BigDecimal(2.00);
	        }
	        else
	        {
	        	BigDecimal maxImporte = new BigDecimal(25); 
	        	importe = (rentJPAEntity.getTotalPrice().getAmount().multiply(maxImporte.divide(new BigDecimal(100))));
	        	    	
	        	int result = importe.compareTo(maxImporte);
	        	
	        	if (result >= 0)
	        	{
	        		importe = maxImporte;
	        	}
	        	
	        	penalizationStatus = PenalizationStatus.Pending;
	        }
	        
	        MoneyV2 money = new MoneyV2(importe);
	        cancellationEntity.setPenalization(money);
	        cancellationEntity.setPenalizationStatus(penalizationStatus);
	        cancellationEntity.setCreationDate(currentDate);
	        cancellationEntity.setRentJPA(rentJPAEntity);
	        
	        try {
	            this.entman.persist(cancellationEntity);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
        }
        
    }


    @Override
    public List<CancellationJPA> listPendingPenalizations() {   	

        List<CancellationJPA> listData = this.entman.createQuery("select c from CancellationJPA c WHERE c.penalizationStatus = :penalizationStatus")
                .setParameter("penalizationStatus", PenalizationStatus.Pending)
                .getResultList();
        return listData;
    }

    @Override
    public void closePenalization(Integer cancellationId) {
        try {
            CancellationJPA cancellationJPAEntity = this.entman.find(CancellationJPA.class, cancellationId);
            cancellationJPAEntity.setPenalizationStatus(PenalizationStatus.Paid);
            this.entman.persist(cancellationJPAEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Date addHoursDate(Date date, int hours)
    {
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.HOUR, hours);
    	return calendar.getTime();
    }
	
}
