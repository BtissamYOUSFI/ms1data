package ma.zyn.app.service.impl.admin.profil;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.profil.EtatInscription;
import ma.zyn.app.dao.criteria.core.profil.EtatInscriptionCriteria;
import ma.zyn.app.dao.facade.core.profil.EtatInscriptionDao;
import ma.zyn.app.dao.specification.core.profil.EtatInscriptionSpecification;
import ma.zyn.app.service.facade.admin.profil.EtatInscriptionAdminService;
import ma.zyn.app.zynerator.service.AbstractServiceImpl;
import static ma.zyn.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.zyn.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class EtatInscriptionAdminServiceImpl implements EtatInscriptionAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtatInscription update(EtatInscription t) {
        EtatInscription loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{EtatInscription.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public EtatInscription findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public EtatInscription findOrSave(EtatInscription t) {
        if (t != null) {
            EtatInscription result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<EtatInscription> findAll() {
        return dao.findAll();
    }

    public List<EtatInscription> findByCriteria(EtatInscriptionCriteria criteria) {
        List<EtatInscription> content = null;
        if (criteria != null) {
            EtatInscriptionSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private EtatInscriptionSpecification constructSpecification(EtatInscriptionCriteria criteria) {
        EtatInscriptionSpecification mySpecification =  (EtatInscriptionSpecification) RefelexivityUtil.constructObjectUsingOneParam(EtatInscriptionSpecification.class, criteria);
        return mySpecification;
    }

    public List<EtatInscription> findPaginatedByCriteria(EtatInscriptionCriteria criteria, int page, int pageSize, String order, String sortField) {
        EtatInscriptionSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EtatInscriptionCriteria criteria) {
        EtatInscriptionSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatInscription> delete(List<EtatInscription> list) {
		List<EtatInscription> result = new ArrayList();
        if (list != null) {
            for (EtatInscription t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtatInscription create(EtatInscription t) {
        EtatInscription loaded = findByReferenceEntity(t);
        EtatInscription saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public EtatInscription findWithAssociatedLists(Long id){
        EtatInscription result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatInscription> update(List<EtatInscription> ts, boolean createIfNotExist) {
        List<EtatInscription> result = new ArrayList<>();
        if (ts != null) {
            for (EtatInscription t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    EtatInscription loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, EtatInscription t, EtatInscription loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public EtatInscription findByReferenceEntity(EtatInscription t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<EtatInscription> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<EtatInscription>> getToBeSavedAndToBeDeleted(List<EtatInscription> oldList, List<EtatInscription> newList) {
        List<List<EtatInscription>> result = new ArrayList<>();
        List<EtatInscription> resultDelete = new ArrayList<>();
        List<EtatInscription> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<EtatInscription> oldList, List<EtatInscription> newList, List<EtatInscription> resultUpdateOrSave, List<EtatInscription> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                EtatInscription myOld = oldList.get(i);
                EtatInscription t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                EtatInscription myNew = newList.get(i);
                EtatInscription t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public EtatInscriptionAdminServiceImpl(EtatInscriptionDao dao) {
        this.dao = dao;
    }

    private EtatInscriptionDao dao;
}
