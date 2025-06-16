package ma.zyn.app.dao.facade.core.utilisateurs;

import ma.zyn.app.bean.core.utilisateurs.Manager;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ManagerDao extends AbstractRepository<Manager,Long>  {
    Manager findByEmail(String email);
    int deleteByEmail(String email);

    Manager findByUsername(String username);

    @Query("SELECT NEW Manager(item.id,item.email) FROM Manager item")
    List<Manager> findAllOptimized();

}
