package managedbean.catalog;

import ejb.rent.RentFacade;
import jpa.ProductJPA;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;


@Named("listProductAvailableItems")
@SessionScoped
public class ListProductAvailableItems implements Serializable {


    private static final String DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
    private static final DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String idModel;

    private String description;

    private String color;

    private String dateStart;

    public Collection<ProductJPA> getListProducts() {
        return listProducts;
    }

    public void setListProducts(Collection<ProductJPA> listProducts) {
        this.listProducts = listProducts;
    }

    private Collection<ProductJPA> listProducts;

    public boolean isUseDateFiler() {
        return useDateFiler;
    }

    public void setUseDateFiler(boolean useDateFiler) {
        this.useDateFiler = useDateFiler;
    }

    private boolean useDateFiler;

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    private String dateEnd;


    @EJB
    private RentFacade rentFacade;

    private String idBrand;

    public String getIdBrand() {
        return idBrand;
    }

    public void setIdBrand(String idBrand) {
        this.idBrand = idBrand;
    }

    public String getIdModel() {
        return idModel;
    }

    public void setIdModel(String idModel) {
        this.idModel = idModel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public void searchProductWithAvailableItems() {
        Integer idIntegerBrand = new Integer(Integer.parseInt(idBrand));
        Integer idIntegerModel = new Integer(Integer.parseInt(idModel));
        Date dateFromLocal = this.transformStringToDate(this.dateStart);
        Date dateToLocal = this.transformStringToDate(this.dateEnd);
        this.listProducts = this.rentFacade.listProductsWithAvailableItems(dateFromLocal, dateToLocal, idIntegerBrand, idIntegerModel, this.description);
        this.useDateFiler = false;
    }

    private Date transformStringToDate(String dateString) {
        if (dateString == null) {
            return null;
        }
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }
}
