package ma.zyn.app.service.facade.admin.profil;

import java.util.List;
import ma.zyn.app.bean.core.profil.NiveauLangue;
import ma.zyn.app.dao.criteria.core.profil.NiveauLangueCriteria;
import ma.zyn.app.zynerator.service.IService;



public interface NiveauLangueAdminService {







	NiveauLangue create(NiveauLangue t);

    NiveauLangue update(NiveauLangue t);

    List<NiveauLangue> update(List<NiveauLangue> ts,boolean createIfNotExist);

    NiveauLangue findById(Long id);

    NiveauLangue findOrSave(NiveauLangue t);

    NiveauLangue findByReferenceEntity(NiveauLangue t);

    NiveauLangue findWithAssociatedLists(Long id);

    List<NiveauLangue> findAllOptimized();

    List<NiveauLangue> findAll();

    List<NiveauLangue> findByCriteria(NiveauLangueCriteria criteria);

    List<NiveauLangue> findPaginatedByCriteria(NiveauLangueCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(NiveauLangueCriteria criteria);

    List<NiveauLangue> delete(List<NiveauLangue> ts);

    boolean deleteById(Long id);

    List<List<NiveauLangue>> getToBeSavedAndToBeDeleted(List<NiveauLangue> oldList, List<NiveauLangue> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

    NiveauLangue findByCode(String code);
}
