package managedbean.rent;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import auth.util.SessionManager;
import ejb.rent.RentFacadeRemote;

@Named("rentBean")
@SessionScoped
public class RentMBean implements Serializable {

	private final static Logger LOG = Logger.getLogger("managedbean.RentMBean");
	private static final long serialVersionUID = 7360539836072273778L;

	@EJB
	private RentFacadeRemote<?> rentFacadeEJB;

	private String fromDateStr;
	private String toDateStr;

	public String createRent(String dateFromStr, String dateToStr) {
		if (!SessionManager.hasUser()) {
			return "/pages/login.xhmtl";
		}
		rentFacadeEJB = getRentFacadeRemote();
		Date dateFrom;
		Date dateTo;
		Date now = new Date();
		try {
			dateFrom = new SimpleDateFormat("MM/dd/yyyy").parse(dateFromStr);
			dateTo = new SimpleDateFormat("MM/dd/yyyy").parse(dateToStr);
			if(dateFrom.before(dateTo) && dateFrom.after(now)) {
				rentFacadeEJB.createRent(dateFrom, dateTo);
				return "/pages/listAllRentsView.xhtml?faces-redirect=true&dateSuccess=Alquiler creado con éxito";
			} else {
				return "/pages/listAllRentsView.xhmtl?faces-redirect=true&dateError=La fecha de inicio no puede ser superior a la fecha fin ni inferior o igual a la fecha actual.";
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return "/pages/listAllRentsView.xhmtl?faces-redirect=true&dateError=Introduce fechas validas";
		}
	}

	public String getFromDateStr() {
		return fromDateStr;
	}

	public void setFromDateStr(String fromDateStr) {
		this.fromDateStr = fromDateStr;
	}

	public String getToDateStr() {
		return toDateStr;
	}

	public void setToDateStr(String toDateStr) {
		this.toDateStr = toDateStr;
	}

	public RentFacadeRemote getRentFacadeRemote() {
		Properties properties = System.getProperties();
		try {
			Context ctx = new InitialContext(properties);
			this.rentFacadeEJB = (RentFacadeRemote) ctx
					.lookup("java:app/PhotoFilmYou.war/RentFacadeBean!ejb.rent.RentFacadeRemote");
			return this.rentFacadeEJB;
		} catch (NamingException e) {
			LOG.info("Catalog not found reason: " + e.getMessage().toString());
		}
		LOG.info("returning null context, en exception threw");
		return null;
	}

}
