package ma.zyn.app.service.facade.manager.transaction;

import ma.zyn.app.bean.core.transaction.StatusPaiement;
import ma.zyn.app.dao.criteria.core.transaction.StatusPaiementCriteria;

import java.util.List;


public interface StatusPaiementManagerService {







	StatusPaiement create(StatusPaiement t);

    StatusPaiement update(StatusPaiement t);

    List<StatusPaiement> update(List<StatusPaiement> ts,boolean createIfNotExist);

    StatusPaiement findById(Long id);

    StatusPaiement findOrSave(StatusPaiement t);

    StatusPaiement findByReferenceEntity(StatusPaiement t);

    StatusPaiement findWithAssociatedLists(Long id);

    List<StatusPaiement> findAllOptimized();

    List<StatusPaiement> findAll();

    List<StatusPaiement> findByCriteria(StatusPaiementCriteria criteria);

    List<StatusPaiement> findPaginatedByCriteria(StatusPaiementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(StatusPaiementCriteria criteria);

    List<StatusPaiement> delete(List<StatusPaiement> ts);

    boolean deleteById(Long id);

    List<List<StatusPaiement>> getToBeSavedAndToBeDeleted(List<StatusPaiement> oldList, List<StatusPaiement> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
