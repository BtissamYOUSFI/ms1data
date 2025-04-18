package ma.zyn.app.service.facade.collaborateur.transaction;

import java.util.List;
import ma.zyn.app.bean.core.transaction.Paiement;
import ma.zyn.app.dao.criteria.core.transaction.PaiementCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface PaiementCollaborateurService {



    List<Paiement> findByMoyenPaiementCode(String code);
    List<Paiement> findByMoyenPaiementId(Long id);
    int deleteByMoyenPaiementId(Long id);
    int deleteByMoyenPaiementCode(String code);
    long countByMoyenPaiementCode(String code);
    List<Paiement> findByStatusPaiementCode(String code);
    List<Paiement> findByStatusPaiementId(Long id);
    int deleteByStatusPaiementId(Long id);
    int deleteByStatusPaiementCode(String code);
    long countByStatusPaiementCode(String code);




	Paiement create(Paiement t);

    Paiement update(Paiement t);

    List<Paiement> update(List<Paiement> ts,boolean createIfNotExist);

    Paiement findById(Long id);

    Paiement findOrSave(Paiement t);

    Paiement findByReferenceEntity(Paiement t);

    Paiement findWithAssociatedLists(Long id);

    List<Paiement> findAllOptimized();

    List<Paiement> findAll();

    List<Paiement> findByCriteria(PaiementCriteria criteria);

    List<Paiement> findPaginatedByCriteria(PaiementCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(PaiementCriteria criteria);

    List<Paiement> delete(List<Paiement> ts);

    boolean deleteById(Long id);

    List<List<Paiement>> getToBeSavedAndToBeDeleted(List<Paiement> oldList, List<Paiement> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
