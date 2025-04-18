package ma.zyn.app.dao.facade.core.transaction;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.transaction.Paiement;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.transaction.Paiement;
import java.util.List;


@Repository
public interface PaiementDao extends AbstractRepository<Paiement,Long>  {
    Paiement findByCode(String code);
    int deleteByCode(String code);

    List<Paiement> findByMoyenPaiementCode(String code);
    List<Paiement> findByMoyenPaiementId(Long id);
    int deleteByMoyenPaiementId(Long id);
    int deleteByMoyenPaiementCode(String code);
    long countByMoyenPaiementCode(String code);
    List<Paiement> findByStatusPaiementCode(String code);
    List<Paiement> findByStatusPaiementId(Long id);
    int deleteByStatusPaiementId(Long id);
    int deleteByStatusPaiementCode(String code);
    long countByStatusPaiementCode(String code);

    @Query("SELECT NEW Paiement(item.id,item.libelle) FROM Paiement item")
    List<Paiement> findAllOptimized();

}
