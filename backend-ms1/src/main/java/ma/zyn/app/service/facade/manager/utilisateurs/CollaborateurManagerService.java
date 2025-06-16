package ma.zyn.app.service.facade.manager.utilisateurs;

import ma.zyn.app.bean.core.utilisateurs.Collaborateur;
import ma.zyn.app.dao.criteria.core.utilisateurs.CollaborateurCriteria;

import java.util.List;


public interface CollaborateurManagerService {


    Collaborateur findByUsername(String username);
    boolean changePassword(String username, String newPassword);





	Collaborateur create(Collaborateur t);

    Collaborateur update(Collaborateur t);

    List<Collaborateur> update(List<Collaborateur> ts,boolean createIfNotExist);

    Collaborateur findById(Long id);

    Collaborateur findOrSave(Collaborateur t);

    Collaborateur findByReferenceEntity(Collaborateur t);

    Collaborateur findWithAssociatedLists(Long id);

    List<Collaborateur> findAllOptimized();

    List<Collaborateur> findAll();

    List<Collaborateur> findByCriteria(CollaborateurCriteria criteria);

    List<Collaborateur> findPaginatedByCriteria(CollaborateurCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(CollaborateurCriteria criteria);

    List<Collaborateur> delete(List<Collaborateur> ts);

    boolean deleteById(Long id);

    List<List<Collaborateur>> getToBeSavedAndToBeDeleted(List<Collaborateur> oldList, List<Collaborateur> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
