package ma.zyn.app.service.impl.admin.transaction;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.transaction.StatusPaiement;
import ma.zyn.app.dao.criteria.core.transaction.StatusPaiementCriteria;
import ma.zyn.app.dao.facade.core.transaction.StatusPaiementDao;
import ma.zyn.app.dao.specification.core.transaction.StatusPaiementSpecification;
import ma.zyn.app.service.facade.admin.transaction.StatusPaiementAdminService;
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
public class StatusPaiementAdminServiceImpl implements StatusPaiementAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public StatusPaiement update(StatusPaiement t) {
        StatusPaiement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{StatusPaiement.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public StatusPaiement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public StatusPaiement findOrSave(StatusPaiement t) {
        if (t != null) {
            StatusPaiement result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<StatusPaiement> findAll() {
        return dao.findAll();
    }

    public List<StatusPaiement> findByCriteria(StatusPaiementCriteria criteria) {
        List<StatusPaiement> content = null;
        if (criteria != null) {
            StatusPaiementSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private StatusPaiementSpecification constructSpecification(StatusPaiementCriteria criteria) {
        StatusPaiementSpecification mySpecification =  (StatusPaiementSpecification) RefelexivityUtil.constructObjectUsingOneParam(StatusPaiementSpecification.class, criteria);
        return mySpecification;
    }

    public List<StatusPaiement> findPaginatedByCriteria(StatusPaiementCriteria criteria, int page, int pageSize, String order, String sortField) {
        StatusPaiementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(StatusPaiementCriteria criteria) {
        StatusPaiementSpecification mySpecification = constructSpecification(criteria);
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
    public List<StatusPaiement> delete(List<StatusPaiement> list) {
		List<StatusPaiement> result = new ArrayList();
        if (list != null) {
            for (StatusPaiement t : list) {
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
    public StatusPaiement create(StatusPaiement t) {
        StatusPaiement loaded = findByReferenceEntity(t);
        StatusPaiement saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public StatusPaiement findWithAssociatedLists(Long id){
        StatusPaiement result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<StatusPaiement> update(List<StatusPaiement> ts, boolean createIfNotExist) {
        List<StatusPaiement> result = new ArrayList<>();
        if (ts != null) {
            for (StatusPaiement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    StatusPaiement loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, StatusPaiement t, StatusPaiement loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public StatusPaiement findByReferenceEntity(StatusPaiement t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<StatusPaiement> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<StatusPaiement>> getToBeSavedAndToBeDeleted(List<StatusPaiement> oldList, List<StatusPaiement> newList) {
        List<List<StatusPaiement>> result = new ArrayList<>();
        List<StatusPaiement> resultDelete = new ArrayList<>();
        List<StatusPaiement> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<StatusPaiement> oldList, List<StatusPaiement> newList, List<StatusPaiement> resultUpdateOrSave, List<StatusPaiement> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                StatusPaiement myOld = oldList.get(i);
                StatusPaiement t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                StatusPaiement myNew = newList.get(i);
                StatusPaiement t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public StatusPaiementAdminServiceImpl(StatusPaiementDao dao) {
        this.dao = dao;
    }

    private StatusPaiementDao dao;
}
