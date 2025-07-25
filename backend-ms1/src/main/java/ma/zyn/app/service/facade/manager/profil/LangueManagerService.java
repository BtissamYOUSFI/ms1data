package ma.zyn.app.service.facade.manager.profil;

import ma.zyn.app.bean.core.profil.Langue;
import ma.zyn.app.dao.criteria.core.profil.LangueCriteria;

import java.util.List;


public interface LangueManagerService {







	Langue create(Langue t);

    Langue update(Langue t);

    List<Langue> update(List<Langue> ts,boolean createIfNotExist);

    Langue findById(Long id);

    Langue findOrSave(Langue t);

    Langue findByReferenceEntity(Langue t);

    Langue findWithAssociatedLists(Long id);

    List<Langue> findAllOptimized();

    List<Langue> findAll();

    List<Langue> findByCriteria(LangueCriteria criteria);

    List<Langue> findPaginatedByCriteria(LangueCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(LangueCriteria criteria);

    List<Langue> delete(List<Langue> ts);

    boolean deleteById(Long id);

    List<List<Langue>> getToBeSavedAndToBeDeleted(List<Langue> oldList, List<Langue> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
