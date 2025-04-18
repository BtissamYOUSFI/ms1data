package ma.zyn.app.dao.facade.core.profil;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.profil.EtatInscription;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.profil.EtatInscription;
import java.util.List;


@Repository
public interface EtatInscriptionDao extends AbstractRepository<EtatInscription,Long>  {
    EtatInscription findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW EtatInscription(item.id,item.libelle) FROM EtatInscription item")
    List<EtatInscription> findAllOptimized();

}
