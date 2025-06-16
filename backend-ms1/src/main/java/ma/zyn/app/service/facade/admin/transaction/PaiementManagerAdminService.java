package ma.zyn.app.service.facade.admin.transaction;

import ma.zyn.app.bean.core.transaction.PaiementManager;
import ma.zyn.app.dao.criteria.core.transaction.PaiementManagerCriteria;

import java.util.List;


public interface PaiementManagerAdminService {



    List<PaiementManager> findByMoyenPaiementCode(String code);
    List<PaiementManager> findByMoyenPaiementId(Long id);
    int deleteByMoyenPaiementId(Long id);
    int deleteByMoyenPaiementCode(String code);
    long countByMoyenPaiementCode(String code);
    List<PaiementManager> findByStatusPaiementCode(String code);
    List<PaiementManager> findByStatusPaiementId(Long id);
    int deleteByStatusPaiementId(Long id);
    int deleteByStatusPaiementCode(String code);
    long countByStatusPaiementCode(String code);
    List<PaiementManager> findByManagerId(Long id);
    int deleteByManagerId(Long id);
    long countByManagerEmail(String email);




	PaiementManager create(PaiementManager t);

    PaiementManager update(PaiementManager t);

    List<PaiementManager> update(List<PaiementManager> ts,boolean createIfNotExist);

    PaiementManager findById(Long id);

    PaiementManager findOrSave(PaiementManager t);

    PaiementManager findByReferenceEntity(PaiementManager t);

    PaiementManager findWithAssociatedLists(Long id);

    List<PaiementManager> findAllOptimized();

    List<PaiementManager> findAll();

    List<PaiementManager> findByCriteria(PaiementManagerCriteria criteria);

    List<PaiementManager> findPaginatedByCriteria(PaiementManagerCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PaiementManagerCriteria criteria);

    List<PaiementManager> delete(List<PaiementManager> ts);

    boolean deleteById(Long id);

    List<List<PaiementManager>> getToBeSavedAndToBeDeleted(List<PaiementManager> oldList, List<PaiementManager> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
