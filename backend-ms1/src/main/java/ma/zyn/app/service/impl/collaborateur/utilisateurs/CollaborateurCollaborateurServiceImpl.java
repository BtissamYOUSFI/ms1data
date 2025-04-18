package ma.zyn.app.service.impl.collaborateur.utilisateurs;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.utilisateurs.Collaborateur;
import ma.zyn.app.dao.criteria.core.utilisateurs.CollaborateurCriteria;
import ma.zyn.app.dao.facade.core.utilisateurs.CollaborateurDao;
import ma.zyn.app.dao.specification.core.utilisateurs.CollaborateurSpecification;
import ma.zyn.app.service.facade.collaborateur.utilisateurs.CollaborateurCollaborateurService;
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


import java.time.LocalDateTime;
import ma.zyn.app.zynerator.security.service.facade.UserService;
import ma.zyn.app.zynerator.security.service.facade.RoleService;
import ma.zyn.app.zynerator.security.service.facade.RoleUserService;
import ma.zyn.app.zynerator.security.bean.Role;
import ma.zyn.app.zynerator.security.bean.RoleUser;
import ma.zyn.app.zynerator.security.common.AuthoritiesConstants;
import ma.zyn.app.zynerator.security.service.facade.ModelPermissionUserService;
import java.util.Collection;
import java.util.List;
@Service
public class CollaborateurCollaborateurServiceImpl implements CollaborateurCollaborateurService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Collaborateur update(Collaborateur t) {
        Collaborateur loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Collaborateur.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Collaborateur findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Collaborateur findOrSave(Collaborateur t) {
        if (t != null) {
            Collaborateur result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Collaborateur> findAll() {
        return dao.findAll();
    }

    public List<Collaborateur> findByCriteria(CollaborateurCriteria criteria) {
        List<Collaborateur> content = null;
        if (criteria != null) {
            CollaborateurSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private CollaborateurSpecification constructSpecification(CollaborateurCriteria criteria) {
        CollaborateurSpecification mySpecification =  (CollaborateurSpecification) RefelexivityUtil.constructObjectUsingOneParam(CollaborateurSpecification.class, criteria);
        return mySpecification;
    }

    public List<Collaborateur> findPaginatedByCriteria(CollaborateurCriteria criteria, int page, int pageSize, String order, String sortField) {
        CollaborateurSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(CollaborateurCriteria criteria) {
        CollaborateurSpecification mySpecification = constructSpecification(criteria);
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
    public List<Collaborateur> delete(List<Collaborateur> list) {
		List<Collaborateur> result = new ArrayList();
        if (list != null) {
            for (Collaborateur t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }


    public Collaborateur findWithAssociatedLists(Long id){
        Collaborateur result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Collaborateur> update(List<Collaborateur> ts, boolean createIfNotExist) {
        List<Collaborateur> result = new ArrayList<>();
        if (ts != null) {
            for (Collaborateur t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Collaborateur loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Collaborateur t, Collaborateur loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Collaborateur findByReferenceEntity(Collaborateur t){
        return t==null? null : dao.findByEmail(t.getEmail());
    }



    public List<Collaborateur> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Collaborateur>> getToBeSavedAndToBeDeleted(List<Collaborateur> oldList, List<Collaborateur> newList) {
        List<List<Collaborateur>> result = new ArrayList<>();
        List<Collaborateur> resultDelete = new ArrayList<>();
        List<Collaborateur> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Collaborateur> oldList, List<Collaborateur> newList, List<Collaborateur> resultUpdateOrSave, List<Collaborateur> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Collaborateur myOld = oldList.get(i);
                Collaborateur t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Collaborateur myNew = newList.get(i);
                Collaborateur t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }


    @Override
    public Collaborateur create(Collaborateur t) {
        if (findByUsername(t.getUsername()) != null || t.getPassword() == null) return null;
        t.setPassword(userService.cryptPassword(t.getPassword()));
        t.setEnabled(true);
        t.setAccountNonExpired(true);
        t.setAccountNonLocked(true);
        t.setCredentialsNonExpired(true);
        t.setPasswordChanged(false);

        Role role = new Role();
        role.setAuthority(AuthoritiesConstants.COLLABORATEUR);
        role.setCreatedAt(LocalDateTime.now());
        Role myRole = roleService.create(role);
        RoleUser roleUser = new RoleUser();
        roleUser.setRole(myRole);
        if (t.getRoleUsers() == null)
            t.setRoleUsers(new ArrayList<>());

        t.getRoleUsers().add(roleUser);
        if (t.getModelPermissionUsers() == null)
            t.setModelPermissionUsers(new ArrayList<>());

        t.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        Collaborateur mySaved = dao.save(t);

        if (t.getModelPermissionUsers() != null) {
            t.getModelPermissionUsers().forEach(e -> {
                e.setUserApp(mySaved);
                modelPermissionUserService.create(e);
            });
        }
        if (t.getRoleUsers() != null) {
            t.getRoleUsers().forEach(element-> {
                element.setUserApp(mySaved);
                roleUserService.create(element);
            });
        }

        return mySaved;
     }

    public Collaborateur findByUsername(String username){
        return dao.findByUsername(username);
    }

    public boolean changePassword(String username, String newPassword) {
        return userService.changePassword(username, newPassword);
    }




    private @Autowired UserService userService;
    private @Autowired RoleService roleService;
    private @Autowired ModelPermissionUserService modelPermissionUserService;
    private @Autowired RoleUserService roleUserService;


    public CollaborateurCollaborateurServiceImpl(CollaborateurDao dao) {
        this.dao = dao;
    }

    private CollaborateurDao dao;
}
