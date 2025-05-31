package ma.zyn.app.dao.facade.core.accompagnement;

import ma.zyn.app.bean.core.accompagnement.TemplateEmailCollaborator;
import ma.zyn.app.zynerator.repository.AbstractRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TemplateEmailCollaboratorDao extends AbstractRepository<TemplateEmailCollaborator,Long>  {
    TemplateEmailCollaborator findBySubject(String subject);
    int deleteBySubject(String subject);


    @Query("SELECT NEW TemplateEmailCollaborator(item.id,item.collaborator) FROM TemplateEmailCollaborator item")
    List<TemplateEmailCollaborator> findAllOptimized();

}
