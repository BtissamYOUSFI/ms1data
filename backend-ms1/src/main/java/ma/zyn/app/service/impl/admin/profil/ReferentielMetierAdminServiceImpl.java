package ma.zyn.app.service.impl.admin.profil;


import ma.zyn.app.zynerator.exception.EntityNotFoundException;
import ma.zyn.app.bean.core.profil.ReferentielMetier;
import ma.zyn.app.dao.criteria.core.profil.ReferentielMetierCriteria;
import ma.zyn.app.dao.facade.core.profil.ReferentielMetierDao;
import ma.zyn.app.dao.specification.core.profil.ReferentielMetierSpecification;
import ma.zyn.app.service.facade.admin.profil.ReferentielMetierAdminService;

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

import ma.zyn.app.service.facade.admin.profil.MetierAdminService ;
import ma.zyn.app.bean.core.profil.Metier ;
import ma.zyn.app.service.facade.admin.profil.NiveauLangueAdminService ;
import ma.zyn.app.bean.core.profil.NiveauLangue ;
import ma.zyn.app.service.facade.admin.profil.LangueAdminService ;
import ma.zyn.app.bean.core.profil.Langue ;

import java.util.Map;

@Service
public class ReferentielMetierAdminServiceImpl implements ReferentielMetierAdminService {

    @Override
    public String importExcelData(List<Map<String, Object>> rows) {
        for (Map<String, Object> row : rows) {
            String code =row.get("code").toString();
            String description = (String) row.get("description");
            String libelle = (String) row.get("libelle");
            Integer nombreHeuresExperienceMin = (Integer) row.get("nombreHeuresExperienceMin");
            Object scelleValue = row.get("scelleRouge");
            Boolean scelleRouge = scelleValue != null && Boolean.parseBoolean(scelleValue.toString());
            String langueCode = (String) row.get("langue");
            String metierCode = (String) row.get("metier");
            String niveauLangueCode = (String) row.get("niveauLangue");
            Langue langue = langueService.findByCode(langueCode);
            Metier metier = metierService.findByCode(metierCode);
            NiveauLangue niveauLangue = niveauLangueService.findByCode(niveauLangueCode);

            ReferentielMetier existing = dao.findByCode(code);
            if (existing != null) {
                continue;
            }

            ReferentielMetier referentielMetier = new ReferentielMetier(
                    code,
                    description,
                    libelle,
                    nombreHeuresExperienceMin,
                    scelleRouge,
                    langue,
                    metier,
                    niveauLangue
            );

            dao.save(referentielMetier);
        }

        return "Import réussi";
    }
//    public String importExcelData(List<ReferentielMetier> list) {
//        for (ReferentielMetier element : list) {
//            Langue langue = langueService.findByCode(element.getLangue().getCode());
//            Metier metier = metierService.findByCode(element.getMetier().getCode());
//            NiveauLangue niveauLangue = niveauLangueService.findByCode(element.getNiveauLangue().getCode());
//
//            ReferentielMetier referentielMetier = new ReferentielMetier(
//                    element.getId(),
//                    element.getCode(),
//                    element.getDescription(),
//                    element.getLibelle(),
//                    element.getNombreHeuresExperienceMin(),
//                    element.getScelleRouge(),
//                    langue,
//                    metier,
//                    niveauLangue
//            );
//
//            dao.save(referentielMetier);
//        }
//
//        return "Données importées avec succès";
//    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ReferentielMetier update(ReferentielMetier t) {
        ReferentielMetier loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{ReferentielMetier.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public ReferentielMetier findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public ReferentielMetier findOrSave(ReferentielMetier t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            ReferentielMetier result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<ReferentielMetier> findAll() {
        return dao.findAll();
    }

    public List<ReferentielMetier> findByCriteria(ReferentielMetierCriteria criteria) {
        List<ReferentielMetier> content = null;
        if (criteria != null) {
            ReferentielMetierSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private ReferentielMetierSpecification constructSpecification(ReferentielMetierCriteria criteria) {
        ReferentielMetierSpecification mySpecification =  (ReferentielMetierSpecification) RefelexivityUtil.constructObjectUsingOneParam(ReferentielMetierSpecification.class, criteria);
        return mySpecification;
    }

    public List<ReferentielMetier> findPaginatedByCriteria(ReferentielMetierCriteria criteria, int page, int pageSize, String order, String sortField) {
        ReferentielMetierSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(ReferentielMetierCriteria criteria) {
        ReferentielMetierSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public ReferentielMetier findByMetierCode(String code){
        return dao.findByMetierCode(code);
    }
    public List<ReferentielMetier> findByMetierId(Long id){
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
    public List<ReferentielMetier> findByLangueCode(String code){
        return dao.findByLangueCode(code);
    }
    public List<ReferentielMetier> findByLangueId(Long id){
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
    public List<ReferentielMetier> findByNiveauLangueCode(String code){
        return dao.findByNiveauLangueCode(code);
    }
    public List<ReferentielMetier> findByNiveauLangueId(Long id){
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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ReferentielMetier> delete(List<ReferentielMetier> list) {
		List<ReferentielMetier> result = new ArrayList();
        if (list != null) {
            for (ReferentielMetier t : list) {
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
    public ReferentielMetier create(ReferentielMetier t) {
        ReferentielMetier loaded = findByReferenceEntity(t);
        ReferentielMetier saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public ReferentielMetier findWithAssociatedLists(Long id){
        ReferentielMetier result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<ReferentielMetier> update(List<ReferentielMetier> ts, boolean createIfNotExist) {
        List<ReferentielMetier> result = new ArrayList<>();
        if (ts != null) {
            for (ReferentielMetier t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    ReferentielMetier loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, ReferentielMetier t, ReferentielMetier loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public ReferentielMetier findByReferenceEntity(ReferentielMetier t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(ReferentielMetier t){
        if( t != null) {
            t.setMetier(metierService.findOrSave(t.getMetier()));
            t.setLangue(langueService.findOrSave(t.getLangue()));
            t.setNiveauLangue(niveauLangueService.findOrSave(t.getNiveauLangue()));
        }
    }



    public List<ReferentielMetier> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<ReferentielMetier>> getToBeSavedAndToBeDeleted(List<ReferentielMetier> oldList, List<ReferentielMetier> newList) {
        List<List<ReferentielMetier>> result = new ArrayList<>();
        List<ReferentielMetier> resultDelete = new ArrayList<>();
        List<ReferentielMetier> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<ReferentielMetier> oldList, List<ReferentielMetier> newList, List<ReferentielMetier> resultUpdateOrSave, List<ReferentielMetier> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                ReferentielMetier myOld = oldList.get(i);
                ReferentielMetier t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                ReferentielMetier myNew = newList.get(i);
                ReferentielMetier t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private MetierAdminService metierService ;
    @Autowired
    private NiveauLangueAdminService niveauLangueService ;
    @Autowired
    private LangueAdminService langueService ;

    public ReferentielMetierAdminServiceImpl(ReferentielMetierDao dao) {
        this.dao = dao;
    }

    private ReferentielMetierDao dao;
}
