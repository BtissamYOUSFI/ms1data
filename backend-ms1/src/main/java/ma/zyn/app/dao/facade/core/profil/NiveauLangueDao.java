package ma.zyn.app.dao.facade.core.profil;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.profil.NiveauLangue;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.profil.NiveauLangue;
import java.util.List;


@Repository
public interface NiveauLangueDao extends AbstractRepository<NiveauLangue,Long>  {
    NiveauLangue findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW NiveauLangue(item.id,item.libelle) FROM NiveauLangue item")
    List<NiveauLangue> findAllOptimized();

}
