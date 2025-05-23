package ma.zyn.app.service.facade.admin.profil;

import java.util.List;
import ma.zyn.app.bean.core.profil.EtatInscription;
import ma.zyn.app.dao.criteria.core.profil.EtatInscriptionCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface EtatInscriptionAdminService {







	EtatInscription create(EtatInscription t);

    EtatInscription update(EtatInscription t);

    List<EtatInscription> update(List<EtatInscription> ts,boolean createIfNotExist);

    EtatInscription findById(Long id);

    EtatInscription findOrSave(EtatInscription t);

    EtatInscription findByReferenceEntity(EtatInscription t);

    EtatInscription findWithAssociatedLists(Long id);

    List<EtatInscription> findAllOptimized();

    List<EtatInscription> findAll();

    List<EtatInscription> findByCriteria(EtatInscriptionCriteria criteria);

    List<EtatInscription> findPaginatedByCriteria(EtatInscriptionCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EtatInscriptionCriteria criteria);

    List<EtatInscription> delete(List<EtatInscription> ts);

    boolean deleteById(Long id);

    List<List<EtatInscription>> getToBeSavedAndToBeDeleted(List<EtatInscription> oldList, List<EtatInscription> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    EtatInscription findByCode(String refused);
}
