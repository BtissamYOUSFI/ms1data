package ma.zyn.app.service.impl.manager.transaction;


import ma.zyn.app.bean.core.transaction.PaiementManager;
import ma.zyn.app.dao.criteria.core.transaction.PaiementManagerCriteria;
import ma.zyn.app.dao.facade.core.transaction.PaiementManagerDao;
import ma.zyn.app.dao.specification.core.transaction.PaiementManagerSpecification;
import ma.zyn.app.service.facade.manager.transaction.MoyenPaiementManagerService;
import ma.zyn.app.service.facade.manager.transaction.PaiementManagerManagerService;
import ma.zyn.app.service.facade.manager.transaction.StatusPaiementManagerService;
import ma.zyn.app.service.facade.manager.utilisateurs.ManagerManagerService;
import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.zynerator.util.RefelexivityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static ma.zyn.app.zynerator.util.ListUtil.isEmpty;
import static ma.zyn.app.zynerator.util.ListUtil.isNotEmpty;

@Service
public class PaiementManagerManagerServiceImpl implements PaiementManagerManagerService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public PaiementManager update(PaiementManager t) {
        PaiementManager loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{PaiementManager.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public PaiementManager findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public PaiementManager findOrSave(PaiementManager t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            PaiementManager result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<PaiementManager> findAll() {
        return dao.findAll();
    }

    public List<PaiementManager> findByCriteria(PaiementManagerCriteria criteria) {
        List<PaiementManager> content = null;
        if (criteria != null) {
            PaiementManagerSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private PaiementManagerSpecification constructSpecification(PaiementManagerCriteria criteria) {
        PaiementManagerSpecification mySpecification =  (PaiementManagerSpecification) RefelexivityUtil.constructObjectUsingOneParam(PaiementManagerSpecification.class, criteria);
        return mySpecification;
    }

    public List<PaiementManager> findPaginatedByCriteria(PaiementManagerCriteria criteria, int page, int pageSize, String order, String sortField) {
        PaiementManagerSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PaiementManagerCriteria criteria) {
        PaiementManagerSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    @Override
    public List<PaiementManager> findByManagerEmail(String email) {
        return dao.findByManagerEmail(email);
    }

    public List<PaiementManager> findByMoyenPaiementCode(String code){
        return dao.findByMoyenPaiementCode(code);
    }
    public List<PaiementManager> findByMoyenPaiementId(Long id){
        return dao.findByMoyenPaiementId(id);
    }
    public int deleteByMoyenPaiementCode(String code){
        return dao.deleteByMoyenPaiementCode(code);
    }
    public int deleteByMoyenPaiementId(Long id){
        return dao.deleteByMoyenPaiementId(id);
    }
    public long countByMoyenPaiementCode(String code){
        return dao.countByMoyenPaiementCode(code);
    }
    public List<PaiementManager> findByStatusPaiementCode(String code){
        return dao.findByStatusPaiementCode(code);
    }
    public List<PaiementManager> findByStatusPaiementId(Long id){
        return dao.findByStatusPaiementId(id);
    }
    public int deleteByStatusPaiementCode(String code){
        return dao.deleteByStatusPaiementCode(code);
    }
    public int deleteByStatusPaiementId(Long id){
        return dao.deleteByStatusPaiementId(id);
    }
    public long countByStatusPaiementCode(String code){
        return dao.countByStatusPaiementCode(code);
    }
    public List<PaiementManager> findByManagerId(Long id){
        return dao.findByManagerId(id);
    }
    public int deleteByManagerId(Long id){
        return dao.deleteByManagerId(id);
    }
    public long countByManagerEmail(String email){
        return dao.countByManagerEmail(email);
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
    public List<PaiementManager> delete(List<PaiementManager> list) {
		List<PaiementManager> result = new ArrayList();
        if (list != null) {
            for (PaiementManager t : list) {
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
    public PaiementManager create(PaiementManager t) {
        PaiementManager loaded = findByReferenceEntity(t);
        PaiementManager saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public PaiementManager findWithAssociatedLists(Long id){
        PaiementManager result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<PaiementManager> update(List<PaiementManager> ts, boolean createIfNotExist) {
        List<PaiementManager> result = new ArrayList<>();
        if (ts != null) {
            for (PaiementManager t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    PaiementManager loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, PaiementManager t, PaiementManager loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public PaiementManager findByReferenceEntity(PaiementManager t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(PaiementManager t){
        if( t != null) {
            t.setMoyenPaiement(moyenPaiementService.findOrSave(t.getMoyenPaiement()));
            t.setStatusPaiement(statusPaiementService.findOrSave(t.getStatusPaiement()));
            t.setManager(managerService.findOrSave(t.getManager()));
        }
    }



    public List<PaiementManager> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<PaiementManager>> getToBeSavedAndToBeDeleted(List<PaiementManager> oldList, List<PaiementManager> newList) {
        List<List<PaiementManager>> result = new ArrayList<>();
        List<PaiementManager> resultDelete = new ArrayList<>();
        List<PaiementManager> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<PaiementManager> oldList, List<PaiementManager> newList, List<PaiementManager> resultUpdateOrSave, List<PaiementManager> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                PaiementManager myOld = oldList.get(i);
                PaiementManager t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                PaiementManager myNew = newList.get(i);
                PaiementManager t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private MoyenPaiementManagerService moyenPaiementService ;
    @Autowired
    private StatusPaiementManagerService statusPaiementService ;
    @Autowired
    private ManagerManagerService managerService ;

    public PaiementManagerManagerServiceImpl(PaiementManagerDao dao) {
        this.dao = dao;
    }

    private PaiementManagerDao dao;
}
