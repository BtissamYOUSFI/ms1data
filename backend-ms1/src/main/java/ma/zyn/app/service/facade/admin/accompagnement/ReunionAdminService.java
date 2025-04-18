package ma.zyn.app.service.facade.admin.accompagnement;

import java.util.List;
import ma.zyn.app.bean.core.accompagnement.Reunion;
import ma.zyn.app.dao.criteria.core.accompagnement.ReunionCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ReunionAdminService {



    List<Reunion> findByCollaborateurId(Long id);
    int deleteByCollaborateurId(Long id);
    long countByCollaborateurEmail(String email);
    List<Reunion> findByEtatReunionCode(String code);
    List<Reunion> findByEtatReunionId(Long id);
    int deleteByEtatReunionId(Long id);
    int deleteByEtatReunionCode(String code);
    long countByEtatReunionCode(String code);




	Reunion create(Reunion t);

    Reunion update(Reunion t);

    List<Reunion> update(List<Reunion> ts,boolean createIfNotExist);

    Reunion findById(Long id);

    Reunion findOrSave(Reunion t);

    Reunion findByReferenceEntity(Reunion t);

    Reunion findWithAssociatedLists(Long id);

    List<Reunion> findAllOptimized();

    List<Reunion> findAll();

    List<Reunion> findByCriteria(ReunionCriteria criteria);

    List<Reunion> findPaginatedByCriteria(ReunionCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ReunionCriteria criteria);

    List<Reunion> delete(List<Reunion> ts);

    boolean deleteById(Long id);

    List<List<Reunion>> getToBeSavedAndToBeDeleted(List<Reunion> oldList, List<Reunion> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
