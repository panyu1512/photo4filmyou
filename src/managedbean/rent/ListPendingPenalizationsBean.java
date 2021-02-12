package managedbean.rent;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import ejb.rent.RentFacadeRemote;
import jpa.CancellationJPA;
import managedbean.SessionBean;


@Named("listPendingPenalizations")
@SessionScoped
public class ListPendingPenalizationsBean extends SessionBean implements Serializable  {
    private static final long serialVersionUID = 1L;

    @EJB
    private RentFacadeRemote<CancellationJPA> catalogFacadeRemote;

    public List<CancellationJPA> getListCancellationPending() {

        try {
            this.checkUserIsNotAdministrator();
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return this.catalogFacadeRemote.listPendingPenalizations();
    }

}
