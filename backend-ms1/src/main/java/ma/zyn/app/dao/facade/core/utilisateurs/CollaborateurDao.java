package ma.zyn.app.dao.facade.core.utilisateurs;

import org.springframework.data.jpa.repository.Query;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import ma.zyn.app.bean.core.utilisateurs.Collaborateur;
import org.springframework.stereotype.Repository;
import ma.zyn.app.bean.core.utilisateurs.Collaborateur;
import java.util.List;


@Repository
public interface CollaborateurDao extends AbstractRepository<Collaborateur,Long>  {
    Collaborateur findByEmail(String email);
    int deleteByEmail(String email);

    Collaborateur findByUsername(String username);

    @Query("SELECT NEW Collaborateur(item.id,item.email) FROM Collaborateur item")
    List<Collaborateur> findAllOptimized();

}
