package ma.zyn.app.service.facade.admin.profil;

import java.util.List;
import ma.zyn.app.bean.core.profil.Inscription;
import ma.zyn.app.dao.criteria.core.profil.InscriptionCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface InscriptionAdminService {



    List<Inscription> findByLangueCode(String code);
    List<Inscription> findByLangueId(Long id);
    int deleteByLangueId(Long id);
    int deleteByLangueCode(String code);
    long countByLangueCode(String code);
    List<Inscription> findByNiveauLangueCode(String code);
    List<Inscription> findByNiveauLangueId(Long id);
    int deleteByNiveauLangueId(Long id);
    int deleteByNiveauLangueCode(String code);
    long countByNiveauLangueCode(String code);
    List<Inscription> findByMetierCode(String code);
    List<Inscription> findByMetierId(Long id);
    int deleteByMetierId(Long id);
    int deleteByMetierCode(String code);
    long countByMetierCode(String code);
    List<Inscription> findByEtatInscriptionCode(String code);
    List<Inscription> findByEtatInscriptionId(Long id);
    int deleteByEtatInscriptionId(Long id);
    int deleteByEtatInscriptionCode(String code);
    long countByEtatInscriptionCode(String code);
    List<Inscription> findByCollaborateurId(Long id);
    int deleteByCollaborateurId(Long id);
    long countByCollaborateurEmail(String email);




	Inscription create(Inscription t);

    Inscription update(Inscription t);

    List<Inscription> update(List<Inscription> ts,boolean createIfNotExist);

    Inscription findById(Long id);

    Inscription findOrSave(Inscription t);

    Inscription findByReferenceEntity(Inscription t);

    Inscription findWithAssociatedLists(Long id);


    List<Inscription> findAll();

    List<Inscription> findByCriteria(InscriptionCriteria criteria);

    List<Inscription> findPaginatedByCriteria(InscriptionCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(InscriptionCriteria criteria);

    List<Inscription> delete(List<Inscription> ts);

    boolean deleteById(Long id);

    List<List<Inscription>> getToBeSavedAndToBeDeleted(List<Inscription> oldList, List<Inscription> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
