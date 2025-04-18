package ma.zyn.app.service.impl.collaborateur.transaction;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.transaction.MoyenPaiement;
import ma.zyn.app.dao.criteria.core.transaction.MoyenPaiementCriteria;
import ma.zyn.app.dao.facade.core.transaction.MoyenPaiementDao;
import ma.zyn.app.dao.specification.core.transaction.MoyenPaiementSpecification;
import ma.zyn.app.service.facade.collaborateur.transaction.MoyenPaiementCollaborateurService;
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
public class MoyenPaiementCollaborateurServiceImpl implements MoyenPaiementCollaborateurService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public MoyenPaiement update(MoyenPaiement t) {
        MoyenPaiement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{MoyenPaiement.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public MoyenPaiement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public MoyenPaiement findOrSave(MoyenPaiement t) {
        if (t != null) {
            MoyenPaiement result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<MoyenPaiement> findAll() {
        return dao.findAll();
    }

    public List<MoyenPaiement> findByCriteria(MoyenPaiementCriteria criteria) {
        List<MoyenPaiement> content = null;
        if (criteria != null) {
            MoyenPaiementSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private MoyenPaiementSpecification constructSpecification(MoyenPaiementCriteria criteria) {
        MoyenPaiementSpecification mySpecification =  (MoyenPaiementSpecification) RefelexivityUtil.constructObjectUsingOneParam(MoyenPaiementSpecification.class, criteria);
        return mySpecification;
    }

    public List<MoyenPaiement> findPaginatedByCriteria(MoyenPaiementCriteria criteria, int page, int pageSize, String order, String sortField) {
        MoyenPaiementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(MoyenPaiementCriteria criteria) {
        MoyenPaiementSpecification mySpecification = constructSpecification(criteria);
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
    public List<MoyenPaiement> delete(List<MoyenPaiement> list) {
		List<MoyenPaiement> result = new ArrayList();
        if (list != null) {
            for (MoyenPaiement t : list) {
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
    public MoyenPaiement create(MoyenPaiement t) {
        MoyenPaiement loaded = findByReferenceEntity(t);
        MoyenPaiement saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public MoyenPaiement findWithAssociatedLists(Long id){
        MoyenPaiement result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<MoyenPaiement> update(List<MoyenPaiement> ts, boolean createIfNotExist) {
        List<MoyenPaiement> result = new ArrayList<>();
        if (ts != null) {
            for (MoyenPaiement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    MoyenPaiement loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, MoyenPaiement t, MoyenPaiement loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public MoyenPaiement findByReferenceEntity(MoyenPaiement t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<MoyenPaiement> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<MoyenPaiement>> getToBeSavedAndToBeDeleted(List<MoyenPaiement> oldList, List<MoyenPaiement> newList) {
        List<List<MoyenPaiement>> result = new ArrayList<>();
        List<MoyenPaiement> resultDelete = new ArrayList<>();
        List<MoyenPaiement> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<MoyenPaiement> oldList, List<MoyenPaiement> newList, List<MoyenPaiement> resultUpdateOrSave, List<MoyenPaiement> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                MoyenPaiement myOld = oldList.get(i);
                MoyenPaiement t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                MoyenPaiement myNew = newList.get(i);
                MoyenPaiement t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public MoyenPaiementCollaborateurServiceImpl(MoyenPaiementDao dao) {
        this.dao = dao;
    }

    private MoyenPaiementDao dao;
}
