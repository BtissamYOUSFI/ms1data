package ma.zyn.app.service.facade.admin.accompagnement;

import ma.zyn.app.bean.core.accompagnement.TemplateEmailManager;
import ma.zyn.app.dao.criteria.core.accompagnement.TemplateEmailManagerCriteria;

import java.util.List;


public interface TemplateEmailManagerAdminService {







	TemplateEmailManager create(TemplateEmailManager t);

    TemplateEmailManager update(TemplateEmailManager t);

    List<TemplateEmailManager> update(List<TemplateEmailManager> ts,boolean createIfNotExist);

    TemplateEmailManager findById(Long id);

    TemplateEmailManager findOrSave(TemplateEmailManager t);

    TemplateEmailManager findByReferenceEntity(TemplateEmailManager t);

    TemplateEmailManager findWithAssociatedLists(Long id);

    List<TemplateEmailManager> findAllOptimized();

    List<TemplateEmailManager> findAll();

    List<TemplateEmailManager> findByCriteria(TemplateEmailManagerCriteria criteria);

    List<TemplateEmailManager> findPaginatedByCriteria(TemplateEmailManagerCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TemplateEmailManagerCriteria criteria);

    List<TemplateEmailManager> delete(List<TemplateEmailManager> ts);

    boolean deleteById(Long id);

    List<List<TemplateEmailManager>> getToBeSavedAndToBeDeleted(List<TemplateEmailManager> oldList, List<TemplateEmailManager> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
