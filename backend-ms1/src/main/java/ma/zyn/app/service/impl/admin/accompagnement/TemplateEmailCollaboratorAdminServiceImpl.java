package ma.zyn.app.service.impl.admin.accompagnement;


import ma.zyn.app.bean.core.accompagnement.TemplateEmailCollaborator;
import ma.zyn.app.dao.criteria.core.accompagnement.TemplateEmailCollaboratorCriteria;
import ma.zyn.app.dao.facade.core.accompagnement.TemplateEmailCollaboratorDao;
import ma.zyn.app.dao.specification.core.accompagnement.TemplateEmailCollaboratorSpecification;
import ma.zyn.app.service.facade.admin.accompagnement.TemplateEmailCollaboratorAdminService;
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
public class TemplateEmailCollaboratorAdminServiceImpl implements TemplateEmailCollaboratorAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public TemplateEmailCollaborator update(TemplateEmailCollaborator t) {
        TemplateEmailCollaborator loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{TemplateEmailCollaborator.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public TemplateEmailCollaborator findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public TemplateEmailCollaborator findOrSave(TemplateEmailCollaborator t) {
        if (t != null) {
            TemplateEmailCollaborator result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<TemplateEmailCollaborator> findAll() {
        return dao.findAll();
    }

    public List<TemplateEmailCollaborator> findByCriteria(TemplateEmailCollaboratorCriteria criteria) {
        List<TemplateEmailCollaborator> content = null;
        if (criteria != null) {
            TemplateEmailCollaboratorSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private TemplateEmailCollaboratorSpecification constructSpecification(TemplateEmailCollaboratorCriteria criteria) {
        TemplateEmailCollaboratorSpecification mySpecification =  (TemplateEmailCollaboratorSpecification) RefelexivityUtil.constructObjectUsingOneParam(TemplateEmailCollaboratorSpecification.class, criteria);
        return mySpecification;
    }

    public List<TemplateEmailCollaborator> findPaginatedByCriteria(TemplateEmailCollaboratorCriteria criteria, int page, int pageSize, String order, String sortField) {
        TemplateEmailCollaboratorSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(TemplateEmailCollaboratorCriteria criteria) {
        TemplateEmailCollaboratorSpecification mySpecification = constructSpecification(criteria);
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
    public List<TemplateEmailCollaborator> delete(List<TemplateEmailCollaborator> list) {
		List<TemplateEmailCollaborator> result = new ArrayList();
        if (list != null) {
            for (TemplateEmailCollaborator t : list) {
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
    public TemplateEmailCollaborator create(TemplateEmailCollaborator t) {
        TemplateEmailCollaborator loaded = findByReferenceEntity(t);
        TemplateEmailCollaborator saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public TemplateEmailCollaborator findWithAssociatedLists(Long id){
        TemplateEmailCollaborator result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<TemplateEmailCollaborator> update(List<TemplateEmailCollaborator> ts, boolean createIfNotExist) {
        List<TemplateEmailCollaborator> result = new ArrayList<>();
        if (ts != null) {
            for (TemplateEmailCollaborator t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    TemplateEmailCollaborator loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, TemplateEmailCollaborator t, TemplateEmailCollaborator loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public TemplateEmailCollaborator findByReferenceEntity(TemplateEmailCollaborator t){
        return t==null? null : dao.findBySubject(t.getSubject());
    }



    public List<TemplateEmailCollaborator> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<TemplateEmailCollaborator>> getToBeSavedAndToBeDeleted(List<TemplateEmailCollaborator> oldList, List<TemplateEmailCollaborator> newList) {
        List<List<TemplateEmailCollaborator>> result = new ArrayList<>();
        List<TemplateEmailCollaborator> resultDelete = new ArrayList<>();
        List<TemplateEmailCollaborator> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<TemplateEmailCollaborator> oldList, List<TemplateEmailCollaborator> newList, List<TemplateEmailCollaborator> resultUpdateOrSave, List<TemplateEmailCollaborator> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                TemplateEmailCollaborator myOld = oldList.get(i);
                TemplateEmailCollaborator t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                TemplateEmailCollaborator myNew = newList.get(i);
                TemplateEmailCollaborator t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public TemplateEmailCollaboratorAdminServiceImpl(TemplateEmailCollaboratorDao dao) {
        this.dao = dao;
    }

    private TemplateEmailCollaboratorDao dao;
}
