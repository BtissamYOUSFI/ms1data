package ma.zyn.app.service.facade.collaborateur.utilisateurs;

import ma.zyn.app.bean.core.utilisateurs.Manager;
import ma.zyn.app.dao.criteria.core.utilisateurs.ManagerCriteria;

import java.util.List;


public interface ManagerCollaborateurService {


    Manager findByUsername(String username);
    boolean changePassword(String username, String newPassword);





	Manager create(Manager t);

    Manager update(Manager t);

    List<Manager> update(List<Manager> ts,boolean createIfNotExist);

    Manager findById(Long id);

    Manager findOrSave(Manager t);

    Manager findByReferenceEntity(Manager t);

    Manager findWithAssociatedLists(Long id);

    List<Manager> findAllOptimized();

    List<Manager> findAll();

    List<Manager> findByCriteria(ManagerCriteria criteria);

    List<Manager> findPaginatedByCriteria(ManagerCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(ManagerCriteria criteria);

    List<Manager> delete(List<Manager> ts);

    boolean deleteById(Long id);

    List<List<Manager>> getToBeSavedAndToBeDeleted(List<Manager> oldList, List<Manager> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
