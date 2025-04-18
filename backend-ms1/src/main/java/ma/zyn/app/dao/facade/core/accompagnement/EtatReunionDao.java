package ma.zyn.app.dao.facade.core.accompagnement;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.accompagnement.EtatReunion;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.accompagnement.EtatReunion;
import java.util.List;


@Repository
public interface EtatReunionDao extends AbstractRepository<EtatReunion,Long>  {
    EtatReunion findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW EtatReunion(item.id,item.libelle) FROM EtatReunion item")
    List<EtatReunion> findAllOptimized();

}
