package managedbean;

import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import domain.MoneyV2;
import domain.NIF;
import ejb.rent.RentFacadeRemote;

@Named
@RequestScoped
public class SecurePaymentSystem {

	private final static Logger LOG = Logger.getLogger("managedbean.RentMBean");

	@EJB
	private RentFacadeRemote<?> rentFacadeEJB;

	public void processPayment(Integer rentId, NIF nif, MoneyV2 amount) {
		try {
			LOG.info("******************************************************");
			LOG.info("*************** PAYMENT SYSTEM ***********************");
			LOG.info("******************************************************");
			LOG.info("Contactando con el sistema de pago...");
			LOG.info("Procesando pago");
			LOG.info("Pagador: " + nif.getValue());
			LOG.info("Alquiler: " + rentId);
			LOG.info("...");
			LOG.info("......");
			LOG.info(".........");
			LOG.info("............");
			LOG.info("...............");
			LOG.info("..................");
			LOG.info(".....................");
			LOG.info("........................PAGO COMPLETADO");
			LOG.info("******************************************************");
			LOG.info("******************************************************");
			rentFacadeEJB.paymentDone(rentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
