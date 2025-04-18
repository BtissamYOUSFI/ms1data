package ma.zyn.app.service.impl.collaborateur.profil;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.profil.Langue;
import ma.zyn.app.dao.criteria.core.profil.LangueCriteria;
import ma.zyn.app.dao.facade.core.profil.LangueDao;
import ma.zyn.app.dao.specification.core.profil.LangueSpecification;
import ma.zyn.app.service.facade.collaborateur.profil.LangueCollaborateurService;
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
public class LangueCollaborateurServiceImpl implements LangueCollaborateurService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Langue update(Langue t) {
        Langue loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Langue.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Langue findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Langue findOrSave(Langue t) {
        if (t != null) {
            Langue result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Langue> findAll() {
        return dao.findAll();
    }

    public List<Langue> findByCriteria(LangueCriteria criteria) {
        List<Langue> content = null;
        if (criteria != null) {
            LangueSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private LangueSpecification constructSpecification(LangueCriteria criteria) {
        LangueSpecification mySpecification =  (LangueSpecification) RefelexivityUtil.constructObjectUsingOneParam(LangueSpecification.class, criteria);
        return mySpecification;
    }

    public List<Langue> findPaginatedByCriteria(LangueCriteria criteria, int page, int pageSize, String order, String sortField) {
        LangueSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(LangueCriteria criteria) {
        LangueSpecification mySpecification = constructSpecification(criteria);
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
    public List<Langue> delete(List<Langue> list) {
		List<Langue> result = new ArrayList();
        if (list != null) {
            for (Langue t : list) {
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
    public Langue create(Langue t) {
        Langue loaded = findByReferenceEntity(t);
        Langue saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Langue findWithAssociatedLists(Long id){
        Langue result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Langue> update(List<Langue> ts, boolean createIfNotExist) {
        List<Langue> result = new ArrayList<>();
        if (ts != null) {
            for (Langue t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Langue loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Langue t, Langue loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Langue findByReferenceEntity(Langue t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Langue> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Langue>> getToBeSavedAndToBeDeleted(List<Langue> oldList, List<Langue> newList) {
        List<List<Langue>> result = new ArrayList<>();
        List<Langue> resultDelete = new ArrayList<>();
        List<Langue> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Langue> oldList, List<Langue> newList, List<Langue> resultUpdateOrSave, List<Langue> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Langue myOld = oldList.get(i);
                Langue t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Langue myNew = newList.get(i);
                Langue t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public LangueCollaborateurServiceImpl(LangueDao dao) {
        this.dao = dao;
    }

    private LangueDao dao;
}
