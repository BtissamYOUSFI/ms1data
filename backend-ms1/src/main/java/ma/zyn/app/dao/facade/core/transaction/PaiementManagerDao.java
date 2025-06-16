package ma.zyn.app.dao.facade.core.transaction;

import ma.zyn.app.bean.core.transaction.PaiementManager;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PaiementManagerDao extends AbstractRepository<PaiementManager,Long>  {
    PaiementManager findByCode(String code);
    int deleteByCode(String code);
    List<PaiementManager> findByManagerEmail(String email);
    List<PaiementManager> findByMoyenPaiementCode(String code);
    List<PaiementManager> findByMoyenPaiementId(Long id);
    int deleteByMoyenPaiementId(Long id);
    int deleteByMoyenPaiementCode(String code);
    long countByMoyenPaiementCode(String code);
    List<PaiementManager> findByStatusPaiementCode(String code);
    List<PaiementManager> findByStatusPaiementId(Long id);
    int deleteByStatusPaiementId(Long id);
    int deleteByStatusPaiementCode(String code);
    long countByStatusPaiementCode(String code);
    List<PaiementManager> findByManagerId(Long id);
    int deleteByManagerId(Long id);
    long countByManagerEmail(String email);

    @Query("SELECT NEW PaiementManager(item.id,item.libelle) FROM PaiementManager item")
    List<PaiementManager> findAllOptimized();

}
