package ma.zyn.app.service.facade.collaborateur.transaction;

import java.util.List;
import ma.zyn.app.bean.core.transaction.MoyenPaiement;
import ma.zyn.app.dao.criteria.core.transaction.MoyenPaiementCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface MoyenPaiementCollaborateurService {







	MoyenPaiement create(MoyenPaiement t);

    MoyenPaiement update(MoyenPaiement t);

    List<MoyenPaiement> update(List<MoyenPaiement> ts,boolean createIfNotExist);

    MoyenPaiement findById(Long id);

    MoyenPaiement findOrSave(MoyenPaiement t);

    MoyenPaiement findByReferenceEntity(MoyenPaiement t);

    MoyenPaiement findWithAssociatedLists(Long id);

    List<MoyenPaiement> findAllOptimized();

    List<MoyenPaiement> findAll();

    List<MoyenPaiement> findByCriteria(MoyenPaiementCriteria criteria);

    List<MoyenPaiement> findPaginatedByCriteria(MoyenPaiementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(MoyenPaiementCriteria criteria);

    List<MoyenPaiement> delete(List<MoyenPaiement> ts);

    boolean deleteById(Long id);

    List<List<MoyenPaiement>> getToBeSavedAndToBeDeleted(List<MoyenPaiement> oldList, List<MoyenPaiement> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
