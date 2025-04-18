package ma.zyn.app.service.impl.admin.accompagnement;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.accompagnement.Reunion;
import ma.zyn.app.dao.criteria.core.accompagnement.ReunionCriteria;
import ma.zyn.app.dao.facade.core.accompagnement.ReunionDao;
import ma.zyn.app.dao.specification.core.accompagnement.ReunionSpecification;
import ma.zyn.app.service.facade.admin.accompagnement.ReunionAdminService;
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

import ma.zyn.app.service.facade.admin.accompagnement.EtatReunionAdminService ;
import ma.zyn.app.bean.core.accompagnement.EtatReunion ;
import ma.zyn.app.service.facade.admin.utilisateurs.CollaborateurAdminService ;
import ma.zyn.app.bean.core.utilisateurs.Collaborateur ;

import java.util.List;
@Service
public class ReunionAdminServiceImpl implements ReunionAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Reunion update(Reunion t) {
        Reunion loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Reunion.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Reunion findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Reunion findOrSave(Reunion t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Reunion result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Reunion> findAll() {
        return dao.findAll();
    }

    public List<Reunion> findByCriteria(ReunionCriteria criteria) {
        List<Reunion> content = null;
        if (criteria != null) {
            ReunionSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ReunionSpecification constructSpecification(ReunionCriteria criteria) {
        ReunionSpecification mySpecification =  (ReunionSpecification) RefelexivityUtil.constructObjectUsingOneParam(ReunionSpecification.class, criteria);
        return mySpecification;
    }

    public List<Reunion> findPaginatedByCriteria(ReunionCriteria criteria, int page, int pageSize, String order, String sortField) {
        ReunionSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ReunionCriteria criteria) {
        ReunionSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Reunion> findByCollaborateurId(Long id){
        return dao.findByCollaborateurId(id);
    }
    public int deleteByCollaborateurId(Long id){
        return dao.deleteByCollaborateurId(id);
    }
    public long countByCollaborateurEmail(String email){
        return dao.countByCollaborateurEmail(email);
    }
    public List<Reunion> findByEtatReunionCode(String code){
        return dao.findByEtatReunionCode(code);
    }
    public List<Reunion> findByEtatReunionId(Long id){
        return dao.findByEtatReunionId(id);
    }
    public int deleteByEtatReunionCode(String code){
        return dao.deleteByEtatReunionCode(code);
    }
    public int deleteByEtatReunionId(Long id){
        return dao.deleteByEtatReunionId(id);
    }
    public long countByEtatReunionCode(String code){
        return dao.countByEtatReunionCode(code);
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
    public List<Reunion> delete(List<Reunion> list) {
		List<Reunion> result = new ArrayList();
        if (list != null) {
            for (Reunion t : list) {
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
    public Reunion create(Reunion t) {
        Reunion loaded = findByReferenceEntity(t);
        Reunion saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Reunion findWithAssociatedLists(Long id){
        Reunion result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Reunion> update(List<Reunion> ts, boolean createIfNotExist) {
        List<Reunion> result = new ArrayList<>();
        if (ts != null) {
            for (Reunion t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Reunion loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Reunion t, Reunion loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Reunion findByReferenceEntity(Reunion t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Reunion t){
        if( t != null) {
            t.setCollaborateur(collaborateurService.findOrSave(t.getCollaborateur()));
            t.setEtatReunion(etatReunionService.findOrSave(t.getEtatReunion()));
        }
    }



    public List<Reunion> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Reunion>> getToBeSavedAndToBeDeleted(List<Reunion> oldList, List<Reunion> newList) {
        List<List<Reunion>> result = new ArrayList<>();
        List<Reunion> resultDelete = new ArrayList<>();
        List<Reunion> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Reunion> oldList, List<Reunion> newList, List<Reunion> resultUpdateOrSave, List<Reunion> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Reunion myOld = oldList.get(i);
                Reunion t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Reunion myNew = newList.get(i);
                Reunion t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private EtatReunionAdminService etatReunionService ;
    @Autowired
    private CollaborateurAdminService collaborateurService ;

    public ReunionAdminServiceImpl(ReunionDao dao) {
        this.dao = dao;
    }

    private ReunionDao dao;
}
