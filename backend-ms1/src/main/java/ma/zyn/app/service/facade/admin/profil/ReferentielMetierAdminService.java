package ma.zyn.app.service.facade.admin.profil;

import java.util.List;
import ma.zyn.app.bean.core.profil.ReferentielMetier;
import ma.zyn.app.dao.criteria.core.profil.ReferentielMetierCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface ReferentielMetierAdminService {



    List<ReferentielMetier> findByMetierCode(String code);
    List<ReferentielMetier> findByMetierId(Long id);
    int deleteByMetierId(Long id);
    int deleteByMetierCode(String code);
    long countByMetierCode(String code);
    List<ReferentielMetier> findByLangueCode(String code);
    List<ReferentielMetier> findByLangueId(Long id);
    int deleteByLangueId(Long id);
    int deleteByLangueCode(String code);
    long countByLangueCode(String code);
    List<ReferentielMetier> findByNiveauLangueCode(String code);
    List<ReferentielMetier> findByNiveauLangueId(Long id);
    int deleteByNiveauLangueId(Long id);
    int deleteByNiveauLangueCode(String code);
    long countByNiveauLangueCode(String code);




	ReferentielMetier create(ReferentielMetier t);

    ReferentielMetier update(ReferentielMetier t);

    List<ReferentielMetier> update(List<ReferentielMetier> ts,boolean createIfNotExist);

    ReferentielMetier findById(Long id);

    ReferentielMetier findOrSave(ReferentielMetier t);

    ReferentielMetier findByReferenceEntity(ReferentielMetier t);

    ReferentielMetier findWithAssociatedLists(Long id);

    List<ReferentielMetier> findAllOptimized();

    List<ReferentielMetier> findAll();

    List<ReferentielMetier> findByCriteria(ReferentielMetierCriteria criteria);

    List<ReferentielMetier> findPaginatedByCriteria(ReferentielMetierCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ReferentielMetierCriteria criteria);

    List<ReferentielMetier> delete(List<ReferentielMetier> ts);

    boolean deleteById(Long id);

    List<List<ReferentielMetier>> getToBeSavedAndToBeDeleted(List<ReferentielMetier> oldList, List<ReferentielMetier> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
