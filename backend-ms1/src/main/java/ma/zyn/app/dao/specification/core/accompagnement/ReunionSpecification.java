package  ma.zyn.app.dao.specification.core.accompagnement;

import ma.zyn.app.dao.criteria.core.accompagnement.ReunionCriteria;
import ma.zyn.app.bean.core.accompagnement.Reunion;
import ma.zyn.app.zynerator.specification.AbstractSpecification;


public class ReunionSpecification extends  AbstractSpecification<ReunionCriteria, Reunion>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
        addPredicateFk("collaborateur","id", criteria.getCollaborateur()==null?null:criteria.getCollaborateur().getId());
        addPredicateFk("collaborateur","id", criteria.getCollaborateurs());
        addPredicateFk("collaborateur","email", criteria.getCollaborateur()==null?null:criteria.getCollaborateur().getEmail());
        addPredicateFk("etatReunion","id", criteria.getEtatReunion()==null?null:criteria.getEtatReunion().getId());
        addPredicateFk("etatReunion","id", criteria.getEtatReunions());
        addPredicateFk("etatReunion","code", criteria.getEtatReunion()==null?null:criteria.getEtatReunion().getCode());
        addPredicateFk("manager","id", criteria.getManager()==null?null:criteria.getManager().getId());
        addPredicateFk("manager","id", criteria.getManagers());
        addPredicateFk("manager","email", criteria.getManager()==null?null:criteria.getManager().getEmail());
    }

    public ReunionSpecification(ReunionCriteria criteria) {
        super(criteria);
    }

    public ReunionSpecification(ReunionCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}

//package  ma.zyn.app.dao.specification.core.accompagnement;
//
//import ma.zyn.app.dao.criteria.core.accompagnement.ReunionCriteria;
//import ma.zyn.app.bean.core.accompagnement.Reunion;
//import ma.zyn.app.zynerator.specification.AbstractSpecification;
//
//
//public class ReunionSpecification extends  AbstractSpecification<ReunionCriteria, Reunion>  {
//
//    @Override
//    public void constructPredicates() {
//        addPredicateId("id", criteria);
//        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
//        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
//        addPredicate("style", criteria.getStyle(),criteria.getStyleLike());
//        addPredicateFk("collaborateur","id", criteria.getCollaborateur()==null?null:criteria.getCollaborateur().getId());
//        addPredicateFk("collaborateur","id", criteria.getCollaborateurs());
//        addPredicateFk("collaborateur","email", criteria.getCollaborateur()==null?null:criteria.getCollaborateur().getEmail());
//        addPredicateFk("etatReunion","id", criteria.getEtatReunion()==null?null:criteria.getEtatReunion().getId());
//        addPredicateFk("etatReunion","id", criteria.getEtatReunions());
//        addPredicateFk("etatReunion","code", criteria.getEtatReunion()==null?null:criteria.getEtatReunion().getCode());
//    }
//
//    public ReunionSpecification(ReunionCriteria criteria) {
//        super(criteria);
//    }
//
//    public ReunionSpecification(ReunionCriteria criteria, boolean distinct) {
//        super(criteria, distinct);
//    }
//
//}
