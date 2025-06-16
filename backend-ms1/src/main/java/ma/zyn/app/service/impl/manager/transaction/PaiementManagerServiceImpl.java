package ma.zyn.app.service.impl.manager.transaction;


import ma.zyn.app.bean.core.transaction.Paiement;
import ma.zyn.app.dao.criteria.core.transaction.PaiementCriteria;
import ma.zyn.app.dao.facade.core.transaction.PaiementDao;
import ma.zyn.app.dao.specification.core.transaction.PaiementSpecification;
import ma.zyn.app.service.facade.manager.transaction.MoyenPaiementManagerService;
import ma.zyn.app.service.facade.manager.transaction.PaiementManagerService;
import ma.zyn.app.service.facade.manager.transaction.StatusPaiementManagerService;
import ma.zyn.app.service.facade.manager.utilisateurs.CollaborateurManagerService;
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
public class PaiementManagerServiceImpl implements PaiementManagerService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Paiement update(Paiement t) {
        Paiement loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Paiement.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Paiement findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Paiement findOrSave(Paiement t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Paiement result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Paiement> findAll() {
        return dao.findAll();
    }

    public List<Paiement> findByCriteria(PaiementCriteria criteria) {
        List<Paiement> content = null;
        if (criteria != null) {
            PaiementSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private PaiementSpecification constructSpecification(PaiementCriteria criteria) {
        PaiementSpecification mySpecification =  (PaiementSpecification) RefelexivityUtil.constructObjectUsingOneParam(PaiementSpecification.class, criteria);
        return mySpecification;
    }

    public List<Paiement> findPaginatedByCriteria(PaiementCriteria criteria, int page, int pageSize, String order, String sortField) {
        PaiementSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(PaiementCriteria criteria) {
        PaiementSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Paiement> findByMoyenPaiementCode(String code){
        return dao.findByMoyenPaiementCode(code);
    }
    public List<Paiement> findByMoyenPaiementId(Long id){
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
    public List<Paiement> findByStatusPaiementCode(String code){
        return dao.findByStatusPaiementCode(code);
    }
    public List<Paiement> findByStatusPaiementId(Long id){
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
    public List<Paiement> findByCollaborateurId(Long id){
        return dao.findByCollaborateurId(id);
    }
    public int deleteByCollaborateurId(Long id){
        return dao.deleteByCollaborateurId(id);
    }
    public long countByCollaborateurEmail(String email){
        return dao.countByCollaborateurEmail(email);
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
    public List<Paiement> delete(List<Paiement> list) {
		List<Paiement> result = new ArrayList();
        if (list != null) {
            for (Paiement t : list) {
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
    public Paiement create(Paiement t) {
        Paiement loaded = findByReferenceEntity(t);
        Paiement saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Paiement findWithAssociatedLists(Long id){
        Paiement result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Paiement> update(List<Paiement> ts, boolean createIfNotExist) {
        List<Paiement> result = new ArrayList<>();
        if (ts != null) {
            for (Paiement t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Paiement loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Paiement t, Paiement loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Paiement findByReferenceEntity(Paiement t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Paiement t){
        if( t != null) {
            t.setMoyenPaiement(moyenPaiementService.findOrSave(t.getMoyenPaiement()));
            t.setStatusPaiement(statusPaiementService.findOrSave(t.getStatusPaiement()));
            t.setCollaborateur(collaborateurService.findOrSave(t.getCollaborateur()));
        }
    }



    public List<Paiement> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Paiement>> getToBeSavedAndToBeDeleted(List<Paiement> oldList, List<Paiement> newList) {
        List<List<Paiement>> result = new ArrayList<>();
        List<Paiement> resultDelete = new ArrayList<>();
        List<Paiement> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Paiement> oldList, List<Paiement> newList, List<Paiement> resultUpdateOrSave, List<Paiement> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Paiement myOld = oldList.get(i);
                Paiement t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Paiement myNew = newList.get(i);
                Paiement t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private CollaborateurManagerService collaborateurService ;

    public PaiementManagerServiceImpl(PaiementDao dao) {
        this.dao = dao;
    }

    private PaiementDao dao;
}
