package ma.zyn.app.dao.facade.core.accompagnement;

import ma.zyn.app.bean.core.accompagnement.TemplateEmailManager;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TemplateEmailManagerDao extends AbstractRepository<TemplateEmailManager,Long>  {
    TemplateEmailManager findBySubject(String subject);
    int deleteBySubject(String subject);


    @Query("SELECT NEW TemplateEmailManager(item.id,item.manager) FROM TemplateEmailManager item")
    List<TemplateEmailManager> findAllOptimized();

}
