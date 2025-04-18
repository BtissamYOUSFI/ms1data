package ma.zyn.app.dao.facade.core.transaction;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.transaction.StatusPaiement;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.transaction.StatusPaiement;
import java.util.List;


@Repository
public interface StatusPaiementDao extends AbstractRepository<StatusPaiement,Long>  {
    StatusPaiement findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW StatusPaiement(item.id,item.libelle) FROM StatusPaiement item")
    List<StatusPaiement> findAllOptimized();

}
