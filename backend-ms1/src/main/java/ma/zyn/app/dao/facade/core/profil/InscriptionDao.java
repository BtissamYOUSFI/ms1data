package ma.zyn.app.dao.facade.core.profil;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.profil.Inscription;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.profil.Inscription;
import java.util.List;


@Repository
public interface InscriptionDao extends AbstractRepository<Inscription,Long>  {
    Inscription findByCode(String code);
    int deleteByCode(String code);

    List<Inscription> findByLangueCode(String code);
    List<Inscription> findByLangueId(Long id);
    int deleteByLangueId(Long id);
    int deleteByLangueCode(String code);
    long countByLangueCode(String code);
    List<Inscription> findByNiveauLangueCode(String code);
    List<Inscription> findByNiveauLangueId(Long id);
    int deleteByNiveauLangueId(Long id);
    int deleteByNiveauLangueCode(String code);
    long countByNiveauLangueCode(String code);
    List<Inscription> findByMetierCode(String code);
    List<Inscription> findByMetierId(Long id);
    int deleteByMetierId(Long id);
    int deleteByMetierCode(String code);
    long countByMetierCode(String code);
    List<Inscription> findByEtatInscriptionCode(String code);
    List<Inscription> findByEtatInscriptionId(Long id);
    int deleteByEtatInscriptionId(Long id);
    int deleteByEtatInscriptionCode(String code);
    long countByEtatInscriptionCode(String code);
    List<Inscription> findByCollaborateurId(Long id);
    int deleteByCollaborateurId(Long id);
    long countByCollaborateurEmail(String email);

    @Query("SELECT NEW Inscription(item.id,item.libelle) FROM Inscription item")
    List<Inscription> findAllOptimized();

}
