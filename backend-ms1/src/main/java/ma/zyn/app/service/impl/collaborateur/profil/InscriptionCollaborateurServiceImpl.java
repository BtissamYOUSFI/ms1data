package ma.zyn.app.service.impl.collaborateur.profil;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.profil.Inscription;
import ma.zyn.app.dao.criteria.core.profil.InscriptionCriteria;
import ma.zyn.app.dao.facade.core.profil.InscriptionDao;
import ma.zyn.app.dao.specification.core.profil.InscriptionSpecification;
import ma.zyn.app.service.facade.collaborateur.profil.InscriptionCollaborateurService;
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

import ma.zyn.app.service.facade.collaborateur.profil.MetierCollaborateurService ;
import ma.zyn.app.bean.core.profil.Metier ;
import ma.zyn.app.service.facade.collaborateur.profil.NiveauLangueCollaborateurService ;
import ma.zyn.app.bean.core.profil.NiveauLangue ;
import ma.zyn.app.service.facade.collaborateur.profil.EtatInscriptionCollaborateurService ;
import ma.zyn.app.bean.core.profil.EtatInscription ;
import ma.zyn.app.service.facade.collaborateur.profil.LangueCollaborateurService ;
import ma.zyn.app.bean.core.profil.Langue ;
import ma.zyn.app.service.facade.collaborateur.utilisateurs.CollaborateurCollaborateurService ;
import ma.zyn.app.bean.core.utilisateurs.Collaborateur ;

import java.util.List;
@Service
public class InscriptionCollaborateurServiceImpl implements InscriptionCollaborateurService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Inscription update(Inscription t) {
        Inscription loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Inscription.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Inscription findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Inscription findOrSave(Inscription t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Inscription result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Inscription> findAll() {
        return dao.findAll();
    }

    public List<Inscription> findByCriteria(InscriptionCriteria criteria) {
        List<Inscription> content = null;
        if (criteria != null) {
            InscriptionSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private InscriptionSpecification constructSpecification(InscriptionCriteria criteria) {
        InscriptionSpecification mySpecification =  (InscriptionSpecification) RefelexivityUtil.constructObjectUsingOneParam(InscriptionSpecification.class, criteria);
        return mySpecification;
    }

    public List<Inscription> findPaginatedByCriteria(InscriptionCriteria criteria, int page, int pageSize, String order, String sortField) {
        InscriptionSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(InscriptionCriteria criteria) {
        InscriptionSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Inscription> findByLangueCode(String code){
        return dao.findByLangueCode(code);
    }
    public List<Inscription> findByLangueId(Long id){
        return dao.findByLangueId(id);
    }
    public int deleteByLangueCode(String code){
        return dao.deleteByLangueCode(code);
    }
    public int deleteByLangueId(Long id){
        return dao.deleteByLangueId(id);
    }
    public long countByLangueCode(String code){
        return dao.countByLangueCode(code);
    }
    public List<Inscription> findByNiveauLangueCode(String code){
        return dao.findByNiveauLangueCode(code);
    }
    public List<Inscription> findByNiveauLangueId(Long id){
        return dao.findByNiveauLangueId(id);
    }
    public int deleteByNiveauLangueCode(String code){
        return dao.deleteByNiveauLangueCode(code);
    }
    public int deleteByNiveauLangueId(Long id){
        return dao.deleteByNiveauLangueId(id);
    }
    public long countByNiveauLangueCode(String code){
        return dao.countByNiveauLangueCode(code);
    }
    public List<Inscription> findByMetierCode(String code){
        return dao.findByMetierCode(code);
    }
    public List<Inscription> findByMetierId(Long id){
        return dao.findByMetierId(id);
    }
    public int deleteByMetierCode(String code){
        return dao.deleteByMetierCode(code);
    }
    public int deleteByMetierId(Long id){
        return dao.deleteByMetierId(id);
    }
    public long countByMetierCode(String code){
        return dao.countByMetierCode(code);
    }
    public List<Inscription> findByEtatInscriptionCode(String code){
        return dao.findByEtatInscriptionCode(code);
    }
    public List<Inscription> findByEtatInscriptionId(Long id){
        return dao.findByEtatInscriptionId(id);
    }
    public int deleteByEtatInscriptionCode(String code){
        return dao.deleteByEtatInscriptionCode(code);
    }
    public int deleteByEtatInscriptionId(Long id){
        return dao.deleteByEtatInscriptionId(id);
    }
    public long countByEtatInscriptionCode(String code){
        return dao.countByEtatInscriptionCode(code);
    }
    public List<Inscription> findByCollaborateurId(Long id){
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
    public List<Inscription> delete(List<Inscription> list) {
		List<Inscription> result = new ArrayList();
        if (list != null) {
            for (Inscription t : list) {
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
    public Inscription create(Inscription t) {
        Inscription loaded = findByReferenceEntity(t);
        Inscription saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Inscription findWithAssociatedLists(Long id){
        Inscription result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Inscription> update(List<Inscription> ts, boolean createIfNotExist) {
        List<Inscription> result = new ArrayList<>();
        if (ts != null) {
            for (Inscription t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Inscription loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Inscription t, Inscription loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Inscription findByReferenceEntity(Inscription t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(Inscription t){
        if( t != null) {
            t.setLangue(langueService.findOrSave(t.getLangue()));
            t.setNiveauLangue(niveauLangueService.findOrSave(t.getNiveauLangue()));
            t.setMetier(metierService.findOrSave(t.getMetier()));
            t.setEtatInscription(etatInscriptionService.findOrSave(t.getEtatInscription()));
            t.setCollaborateur(collaborateurService.findOrSave(t.getCollaborateur()));
        }
    }



    public List<Inscription> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Inscription>> getToBeSavedAndToBeDeleted(List<Inscription> oldList, List<Inscription> newList) {
        List<List<Inscription>> result = new ArrayList<>();
        List<Inscription> resultDelete = new ArrayList<>();
        List<Inscription> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Inscription> oldList, List<Inscription> newList, List<Inscription> resultUpdateOrSave, List<Inscription> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Inscription myOld = oldList.get(i);
                Inscription t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Inscription myNew = newList.get(i);
                Inscription t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private MetierCollaborateurService metierService ;
    @Autowired
    private NiveauLangueCollaborateurService niveauLangueService ;
    @Autowired
    private EtatInscriptionCollaborateurService etatInscriptionService ;
    @Autowired
    private LangueCollaborateurService langueService ;
    @Autowired
    private CollaborateurCollaborateurService collaborateurService ;

    public InscriptionCollaborateurServiceImpl(InscriptionDao dao) {
        this.dao = dao;
    }

    private InscriptionDao dao;
}
