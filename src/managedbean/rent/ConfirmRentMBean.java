package managedbean.rent;

import ejb.rent.RentFacadeRemote;
import jpa.RentJPA;
import managedbean.SecurePaymentSystem;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import auth.util.SessionManager;
import domain.RentStatus;

import java.io.Serializable;

@Named("confirmRentBean")
@SessionScoped
public class ConfirmRentMBean implements Serializable {

	private static final long serialVersionUID = 7360539836072273778L;

	@EJB
	private RentFacadeRemote<?> rentFacadeEJB;
	@Inject
	private SecurePaymentSystem paymentSystem;

	public String confirmRent(int rentId) {
		if (!SessionManager.hasUser()) {
			return "/pages/login.xhmtl";
		}
		try {
			RentJPA rent = rentFacadeEJB.getRent(new Integer(rentId));
			paymentSystem.processPayment(new Integer(rentId), rent.getCustomerJPA().getNif(), rent.getTotalPrice());
			return "/pages/listAllRentsView.xhtml?faces-redirect=true&dateSuccess=Alquiler creado con éxito";
		} catch (Exception e) {
			e.printStackTrace();
			return "/pages/listAllRentsView.xhmtl?faces-redirect=true&dateError=Error procesando el pago";
		}
	}

}
