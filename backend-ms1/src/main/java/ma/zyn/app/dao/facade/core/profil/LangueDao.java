package ma.zyn.app.dao.facade.core.profil;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.profil.Langue;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.profil.Langue;
import java.util.List;


@Repository
public interface LangueDao extends AbstractRepository<Langue,Long>  {
    Langue findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Langue(item.id,item.libelle) FROM Langue item")
    List<Langue> findAllOptimized();

}
