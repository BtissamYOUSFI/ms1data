package ma.zyn.app.dao.facade.core.profil;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.profil.Metier;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.profil.Metier;
import java.util.List;


@Repository
public interface MetierDao extends AbstractRepository<Metier,Long>  {
    Metier findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Metier(item.id,item.libelle) FROM Metier item")
    List<Metier> findAllOptimized();

}
