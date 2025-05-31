package ma.zyn.app.service.impl.admin.accompagnement;


import ma.zyn.app.bean.core.accompagnement.TemplateEmailManager;
import ma.zyn.app.dao.criteria.core.accompagnement.TemplateEmailManagerCriteria;
import ma.zyn.app.dao.facade.core.accompagnement.TemplateEmailManagerDao;
import ma.zyn.app.dao.specification.core.accompagnement.TemplateEmailManagerSpecification;
import ma.zyn.app.service.facade.admin.accompagnement.TemplateEmailManagerAdminService;
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
public class TemplateEmailManagerAdminServiceImpl implements TemplateEmailManagerAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TemplateEmailManager update(TemplateEmailManager t) {
        TemplateEmailManager loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TemplateEmailManager.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TemplateEmailManager findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TemplateEmailManager findOrSave(TemplateEmailManager t) {
        if (t != null) {
            TemplateEmailManager result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TemplateEmailManager> findAll() {
        return dao.findAll();
    }

    public List<TemplateEmailManager> findByCriteria(TemplateEmailManagerCriteria criteria) {
        List<TemplateEmailManager> content = null;
        if (criteria != null) {
            TemplateEmailManagerSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private TemplateEmailManagerSpecification constructSpecification(TemplateEmailManagerCriteria criteria) {
        TemplateEmailManagerSpecification mySpecification =  (TemplateEmailManagerSpecification) RefelexivityUtil.constructObjectUsingOneParam(TemplateEmailManagerSpecification.class, criteria);
        return mySpecification;
    }

    public List<TemplateEmailManager> findPaginatedByCriteria(TemplateEmailManagerCriteria criteria, int page, int pageSize, String order, String sortField) {
        TemplateEmailManagerSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TemplateEmailManagerCriteria criteria) {
        TemplateEmailManagerSpecification mySpecification = constructSpecification(criteria);
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
    public List<TemplateEmailManager> delete(List<TemplateEmailManager> list) {
		List<TemplateEmailManager> result = new ArrayList();
        if (list != null) {
            for (TemplateEmailManager t : list) {
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
    public TemplateEmailManager create(TemplateEmailManager t) {
        TemplateEmailManager loaded = findByReferenceEntity(t);
        TemplateEmailManager saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TemplateEmailManager findWithAssociatedLists(Long id){
        TemplateEmailManager result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TemplateEmailManager> update(List<TemplateEmailManager> ts, boolean createIfNotExist) {
        List<TemplateEmailManager> result = new ArrayList<>();
        if (ts != null) {
            for (TemplateEmailManager t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TemplateEmailManager loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TemplateEmailManager t, TemplateEmailManager loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TemplateEmailManager findByReferenceEntity(TemplateEmailManager t){
        return t==null? null : dao.findBySubject(t.getSubject());
    }



    public List<TemplateEmailManager> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TemplateEmailManager>> getToBeSavedAndToBeDeleted(List<TemplateEmailManager> oldList, List<TemplateEmailManager> newList) {
        List<List<TemplateEmailManager>> result = new ArrayList<>();
        List<TemplateEmailManager> resultDelete = new ArrayList<>();
        List<TemplateEmailManager> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TemplateEmailManager> oldList, List<TemplateEmailManager> newList, List<TemplateEmailManager> resultUpdateOrSave, List<TemplateEmailManager> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TemplateEmailManager myOld = oldList.get(i);
                TemplateEmailManager t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TemplateEmailManager myNew = newList.get(i);
                TemplateEmailManager t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public TemplateEmailManagerAdminServiceImpl(TemplateEmailManagerDao dao) {
        this.dao = dao;
    }

    private TemplateEmailManagerDao dao;
}
