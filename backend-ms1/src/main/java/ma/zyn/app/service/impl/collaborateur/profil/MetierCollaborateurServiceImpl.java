package ma.zyn.app.service.impl.collaborateur.profil;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.profil.Metier;
import ma.zyn.app.dao.criteria.core.profil.MetierCriteria;
import ma.zyn.app.dao.facade.core.profil.MetierDao;
import ma.zyn.app.dao.specification.core.profil.MetierSpecification;
import ma.zyn.app.service.facade.collaborateur.profil.MetierCollaborateurService;
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
public class MetierCollaborateurServiceImpl implements MetierCollaborateurService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Metier update(Metier t) {
        Metier loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Metier.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Metier findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Metier findOrSave(Metier t) {
        if (t != null) {
            Metier result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Metier> findAll() {
        return dao.findAll();
    }

    public List<Metier> findByCriteria(MetierCriteria criteria) {
        List<Metier> content = null;
        if (criteria != null) {
            MetierSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private MetierSpecification constructSpecification(MetierCriteria criteria) {
        MetierSpecification mySpecification =  (MetierSpecification) RefelexivityUtil.constructObjectUsingOneParam(MetierSpecification.class, criteria);
        return mySpecification;
    }

    public List<Metier> findPaginatedByCriteria(MetierCriteria criteria, int page, int pageSize, String order, String sortField) {
        MetierSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(MetierCriteria criteria) {
        MetierSpecification mySpecification = constructSpecification(criteria);
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
    public List<Metier> delete(List<Metier> list) {
		List<Metier> result = new ArrayList();
        if (list != null) {
            for (Metier t : list) {
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
    public Metier create(Metier t) {
        Metier loaded = findByReferenceEntity(t);
        Metier saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Metier findWithAssociatedLists(Long id){
        Metier result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Metier> update(List<Metier> ts, boolean createIfNotExist) {
        List<Metier> result = new ArrayList<>();
        if (ts != null) {
            for (Metier t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Metier loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Metier t, Metier loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Metier findByReferenceEntity(Metier t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Metier> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Metier>> getToBeSavedAndToBeDeleted(List<Metier> oldList, List<Metier> newList) {
        List<List<Metier>> result = new ArrayList<>();
        List<Metier> resultDelete = new ArrayList<>();
        List<Metier> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Metier> oldList, List<Metier> newList, List<Metier> resultUpdateOrSave, List<Metier> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Metier myOld = oldList.get(i);
                Metier t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Metier myNew = newList.get(i);
                Metier t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public MetierCollaborateurServiceImpl(MetierDao dao) {
        this.dao = dao;
    }

    private MetierDao dao;
}
