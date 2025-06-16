package ma.zyn.app.service.facade.manager.accompagnement;

import ma.zyn.app.bean.core.accompagnement.TemplateEmailCollaborator;
import ma.zyn.app.dao.criteria.core.accompagnement.TemplateEmailCollaboratorCriteria;

import java.util.List;


public interface TemplateEmailCollaboratorManagerService {

	TemplateEmailCollaborator create(TemplateEmailCollaborator t);

    TemplateEmailCollaborator update(TemplateEmailCollaborator t);

    List<TemplateEmailCollaborator> update(List<TemplateEmailCollaborator> ts,boolean createIfNotExist);

    TemplateEmailCollaborator findById(Long id);

    TemplateEmailCollaborator findOrSave(TemplateEmailCollaborator t);

    TemplateEmailCollaborator findByReferenceEntity(TemplateEmailCollaborator t);

    TemplateEmailCollaborator findWithAssociatedLists(Long id);

    List<TemplateEmailCollaborator> findAllOptimized();

    List<TemplateEmailCollaborator> findAll();

    List<TemplateEmailCollaborator> findByCriteria(TemplateEmailCollaboratorCriteria criteria);

    List<TemplateEmailCollaborator> findPaginatedByCriteria(TemplateEmailCollaboratorCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TemplateEmailCollaboratorCriteria criteria);

    List<TemplateEmailCollaborator> delete(List<TemplateEmailCollaborator> ts);

    boolean deleteById(Long id);

    List<List<TemplateEmailCollaborator>> getToBeSavedAndToBeDeleted(List<TemplateEmailCollaborator> oldList, List<TemplateEmailCollaborator> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
