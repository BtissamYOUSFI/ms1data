package ma.zyn.app.service.impl.manager.profil;


import ma.zyn.app.bean.core.profil.NiveauLangue;
import ma.zyn.app.dao.criteria.core.profil.NiveauLangueCriteria;
import ma.zyn.app.dao.facade.core.profil.NiveauLangueDao;
import ma.zyn.app.dao.specification.core.profil.NiveauLangueSpecification;
import ma.zyn.app.service.facade.manager.profil.NiveauLangueManagerService;
import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.zynerator.util.RefelexivityUtil;
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
public class NiveauLangueManagerServiceImpl implements NiveauLangueManagerService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public NiveauLangue update(NiveauLangue t) {
        NiveauLangue loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{NiveauLangue.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public NiveauLangue findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public NiveauLangue findOrSave(NiveauLangue t) {
        if (t != null) {
            NiveauLangue result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<NiveauLangue> findAll() {
        return dao.findAll();
    }

    public List<NiveauLangue> findByCriteria(NiveauLangueCriteria criteria) {
        List<NiveauLangue> content = null;
        if (criteria != null) {
            NiveauLangueSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private NiveauLangueSpecification constructSpecification(NiveauLangueCriteria criteria) {
        NiveauLangueSpecification mySpecification =  (NiveauLangueSpecification) RefelexivityUtil.constructObjectUsingOneParam(NiveauLangueSpecification.class, criteria);
        return mySpecification;
    }

    public List<NiveauLangue> findPaginatedByCriteria(NiveauLangueCriteria criteria, int page, int pageSize, String order, String sortField) {
        NiveauLangueSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(NiveauLangueCriteria criteria) {
        NiveauLangueSpecification mySpecification = constructSpecification(criteria);
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
    public List<NiveauLangue> delete(List<NiveauLangue> list) {
		List<NiveauLangue> result = new ArrayList();
        if (list != null) {
            for (NiveauLangue t : list) {
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
    public NiveauLangue create(NiveauLangue t) {
        NiveauLangue loaded = findByReferenceEntity(t);
        NiveauLangue saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public NiveauLangue findWithAssociatedLists(Long id){
        NiveauLangue result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<NiveauLangue> update(List<NiveauLangue> ts, boolean createIfNotExist) {
        List<NiveauLangue> result = new ArrayList<>();
        if (ts != null) {
            for (NiveauLangue t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    NiveauLangue loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, NiveauLangue t, NiveauLangue loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public NiveauLangue findByReferenceEntity(NiveauLangue t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<NiveauLangue> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<NiveauLangue>> getToBeSavedAndToBeDeleted(List<NiveauLangue> oldList, List<NiveauLangue> newList) {
        List<List<NiveauLangue>> result = new ArrayList<>();
        List<NiveauLangue> resultDelete = new ArrayList<>();
        List<NiveauLangue> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<NiveauLangue> oldList, List<NiveauLangue> newList, List<NiveauLangue> resultUpdateOrSave, List<NiveauLangue> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                NiveauLangue myOld = oldList.get(i);
                NiveauLangue t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                NiveauLangue myNew = newList.get(i);
                NiveauLangue t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public NiveauLangueManagerServiceImpl(NiveauLangueDao dao) {
        this.dao = dao;
    }

    private NiveauLangueDao dao;
}
