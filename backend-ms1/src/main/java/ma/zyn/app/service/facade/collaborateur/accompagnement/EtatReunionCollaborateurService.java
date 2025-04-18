package ma.zyn.app.service.facade.collaborateur.accompagnement;

import java.util.List;
import ma.zyn.app.bean.core.accompagnement.EtatReunion;
import ma.zyn.app.dao.criteria.core.accompagnement.EtatReunionCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface EtatReunionCollaborateurService {







	EtatReunion create(EtatReunion t);

    EtatReunion update(EtatReunion t);

    List<EtatReunion> update(List<EtatReunion> ts,boolean createIfNotExist);

    EtatReunion findById(Long id);

    EtatReunion findOrSave(EtatReunion t);

    EtatReunion findByReferenceEntity(EtatReunion t);

    EtatReunion findWithAssociatedLists(Long id);

    List<EtatReunion> findAllOptimized();

    List<EtatReunion> findAll();

    List<EtatReunion> findByCriteria(EtatReunionCriteria criteria);

    List<EtatReunion> findPaginatedByCriteria(EtatReunionCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EtatReunionCriteria criteria);

    List<EtatReunion> delete(List<EtatReunion> ts);

    boolean deleteById(Long id);

    List<List<EtatReunion>> getToBeSavedAndToBeDeleted(List<EtatReunion> oldList, List<EtatReunion> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
