package ma.zyn.app.dao.facade.core.profil;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.profil.ReferentielMetier;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.profil.ReferentielMetier;
import java.util.List;


@Repository
public interface ReferentielMetierDao extends AbstractRepository<ReferentielMetier,Long>  {
    ReferentielMetier findByCode(String code);
    int deleteByCode(String code);

    ReferentielMetier findByMetierCode(String code);
    List<ReferentielMetier> findByMetierId(Long id);
    int deleteByMetierId(Long id);
    int deleteByMetierCode(String code);
    long countByMetierCode(String code);
    List<ReferentielMetier> findByLangueCode(String code);
    List<ReferentielMetier> findByLangueId(Long id);
    int deleteByLangueId(Long id);
    int deleteByLangueCode(String code);
    long countByLangueCode(String code);
    List<ReferentielMetier> findByNiveauLangueCode(String code);
    List<ReferentielMetier> findByNiveauLangueId(Long id);
    int deleteByNiveauLangueId(Long id);
    int deleteByNiveauLangueCode(String code);
    long countByNiveauLangueCode(String code);

    @Query("SELECT NEW ReferentielMetier(item.id,item.libelle) FROM ReferentielMetier item")
    List<ReferentielMetier> findAllOptimized();

}
