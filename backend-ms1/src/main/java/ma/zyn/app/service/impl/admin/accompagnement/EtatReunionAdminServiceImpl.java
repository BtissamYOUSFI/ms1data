package ma.zyn.app.service.impl.admin.accompagnement;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.accompagnement.EtatReunion;
import ma.zyn.app.dao.criteria.core.accompagnement.EtatReunionCriteria;
import ma.zyn.app.dao.facade.core.accompagnement.EtatReunionDao;
import ma.zyn.app.dao.specification.core.accompagnement.EtatReunionSpecification;
import ma.zyn.app.service.facade.admin.accompagnement.EtatReunionAdminService;
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
public class EtatReunionAdminServiceImpl implements EtatReunionAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public EtatReunion update(EtatReunion t) {
        EtatReunion loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{EtatReunion.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public EtatReunion findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public EtatReunion findOrSave(EtatReunion t) {
        if (t != null) {
            EtatReunion result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<EtatReunion> findAll() {
        return dao.findAll();
    }

    public List<EtatReunion> findByCriteria(EtatReunionCriteria criteria) {
        List<EtatReunion> content = null;
        if (criteria != null) {
            EtatReunionSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private EtatReunionSpecification constructSpecification(EtatReunionCriteria criteria) {
        EtatReunionSpecification mySpecification =  (EtatReunionSpecification) RefelexivityUtil.constructObjectUsingOneParam(EtatReunionSpecification.class, criteria);
        return mySpecification;
    }

    public List<EtatReunion> findPaginatedByCriteria(EtatReunionCriteria criteria, int page, int pageSize, String order, String sortField) {
        EtatReunionSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EtatReunionCriteria criteria) {
        EtatReunionSpecification mySpecification = constructSpecification(criteria);
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
    public List<EtatReunion> delete(List<EtatReunion> list) {
		List<EtatReunion> result = new ArrayList();
        if (list != null) {
            for (EtatReunion t : list) {
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
    public EtatReunion create(EtatReunion t) {
        EtatReunion loaded = findByReferenceEntity(t);
        EtatReunion saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public EtatReunion findWithAssociatedLists(Long id){
        EtatReunion result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<EtatReunion> update(List<EtatReunion> ts, boolean createIfNotExist) {
        List<EtatReunion> result = new ArrayList<>();
        if (ts != null) {
            for (EtatReunion t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    EtatReunion loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, EtatReunion t, EtatReunion loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public EtatReunion findByReferenceEntity(EtatReunion t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<EtatReunion> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<EtatReunion>> getToBeSavedAndToBeDeleted(List<EtatReunion> oldList, List<EtatReunion> newList) {
        List<List<EtatReunion>> result = new ArrayList<>();
        List<EtatReunion> resultDelete = new ArrayList<>();
        List<EtatReunion> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<EtatReunion> oldList, List<EtatReunion> newList, List<EtatReunion> resultUpdateOrSave, List<EtatReunion> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                EtatReunion myOld = oldList.get(i);
                EtatReunion t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                EtatReunion myNew = newList.get(i);
                EtatReunion t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public EtatReunionAdminServiceImpl(EtatReunionDao dao) {
        this.dao = dao;
    }

    private EtatReunionDao dao;
}
