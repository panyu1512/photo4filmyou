package ejb.rent;

import domain.RentStatus;
import jpa.*;

import javax.ejb.Remote;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Remote
public interface RentFacadeRemote<T> {
    Collection <ProductJPA> listProductsWithAvailableItems(Date from, Date to, Integer idBrand, Integer idModel, String description);

    void paymentDone(Integer rentId);
    void createRent(Date from, Date to);
    Collection<ItemJPA> listAllRentItems(Integer rentId);    
    void addItemToRent(Integer rentId, String itemId);
    RentJPA getRent(Integer rentId);
    void confirmRent(Integer rentId);
    void updateRentStatus(Integer rentId, RentStatus status);
    void cancelRent(Integer rentId);
    List<CancellationJPA> listPendingPenalizations();
    void closePenalization(Integer cancellationId);
}
