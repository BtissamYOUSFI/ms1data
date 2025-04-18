package ma.zyn.app.dao.facade.core.transaction;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.transaction.MoyenPaiement;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.transaction.MoyenPaiement;
import java.util.List;


@Repository
public interface MoyenPaiementDao extends AbstractRepository<MoyenPaiement,Long>  {
    MoyenPaiement findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW MoyenPaiement(item.id,item.libelle) FROM MoyenPaiement item")
    List<MoyenPaiement> findAllOptimized();

}
