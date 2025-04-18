package ma.zyn.app.dao.facade.core.accompagnement;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.accompagnement.Reunion;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.accompagnement.Reunion;
import java.util.List;


@Repository
public interface ReunionDao extends AbstractRepository<Reunion,Long>  {
    Reunion findByCode(String code);
    int deleteByCode(String code);

    List<Reunion> findByCollaborateurId(Long id);
    int deleteByCollaborateurId(Long id);
    long countByCollaborateurEmail(String email);
    List<Reunion> findByEtatReunionCode(String code);
    List<Reunion> findByEtatReunionId(Long id);
    int deleteByEtatReunionId(Long id);
    int deleteByEtatReunionCode(String code);
    long countByEtatReunionCode(String code);

    @Query("SELECT NEW Reunion(item.id,item.libelle) FROM Reunion item")
    List<Reunion> findAllOptimized();

}
