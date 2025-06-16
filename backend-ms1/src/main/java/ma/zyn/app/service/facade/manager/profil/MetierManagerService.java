package ma.zyn.app.service.facade.manager.profil;

import ma.zyn.app.bean.core.profil.Metier;
import ma.zyn.app.dao.criteria.core.profil.MetierCriteria;

import java.util.List;


public interface MetierManagerService {







	Metier create(Metier t);

    Metier update(Metier t);

    List<Metier> update(List<Metier> ts,boolean createIfNotExist);

    Metier findById(Long id);

    Metier findOrSave(Metier t);

    Metier findByReferenceEntity(Metier t);

    Metier findWithAssociatedLists(Long id);

    List<Metier> findAllOptimized();

    List<Metier> findAll();

    List<Metier> findByCriteria(MetierCriteria criteria);

    List<Metier> findPaginatedByCriteria(MetierCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(MetierCriteria criteria);

    List<Metier> delete(List<Metier> ts);

    boolean deleteById(Long id);

    List<List<Metier>> getToBeSavedAndToBeDeleted(List<Metier> oldList, List<Metier> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
