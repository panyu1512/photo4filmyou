package managedbean.rent;

import auth.UserLoggableInterface;
import auth.util.SessionManager;
import ejb.catalog.CatalogFacadeRemote;
import ejb.profile.ProfileFacadeRemote;
import ejb.rent.RentFacadeRemote;
import jpa.CancellationJPA;
import jpa.ModelJPA;
import managedbean.SessionBean;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;


@Named("closePenalization")
@SessionScoped
public class ClosePenalizationBean extends SessionBean implements Serializable  {
    private static final long serialVersionUID = 1L;

    @EJB
    private RentFacadeRemote<CancellationJPA> rentFacadeRemote;

    public void closePenalization(int idCancellation) {
        this.rentFacadeRemote.closePenalization(idCancellation);
    }

}
